package com.eyes.eyesspace.sync.service.impl;

import com.eyes.eyesAuth.constant.AuthConfigConstant;
import com.eyes.eyesAuth.context.UserInfoHolder;
import com.eyes.eyesTools.common.exception.CustomException;
import com.eyes.eyesTools.service.file.FileUtils;
import com.eyes.eyesTools.utils.SecurityUtils;
import com.eyes.eyesspace.constant.CommentEnum;
import com.eyes.eyesspace.constant.FileMethodEnum;
import com.eyes.eyesspace.constant.FileOperationLogConstant;
import com.eyes.eyesspace.constant.HomeTypeEnum;
import com.eyes.eyesspace.constant.MediaConstant;
import com.eyes.eyesspace.constant.StatusEnum;
import com.eyes.eyesspace.persistent.dto.ShuoshuoInfoDTO;
import com.eyes.eyesspace.persistent.mapper.HomeMapper;
import com.eyes.eyesspace.persistent.mapper.ShuoMapper;
import com.eyes.eyesspace.persistent.mapper.TrackMapper;
import com.eyes.eyesspace.persistent.po.CommentDelInfoPO;
import com.eyes.eyesspace.persistent.po.ShuoDataPO;
import com.eyes.eyesspace.sync.convert.ShuoConvert;
import com.eyes.eyesspace.sync.model.request.CommentAddRequest;
import com.eyes.eyesspace.sync.model.vo.CommentListVO;
import com.eyes.eyesspace.sync.model.vo.FileUploadVO;
import com.eyes.eyesspace.sync.model.request.ShuoAddRequest;
import com.eyes.eyesspace.sync.model.request.ShuoCommentAddRequest;
import com.eyes.eyesspace.sync.model.vo.ShuoListVO;
import com.eyes.eyesspace.sync.model.vo.ShuoListInfoVO;
import com.eyes.eyesspace.sync.service.CommentService;
import com.eyes.eyesspace.sync.service.ShuoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RefreshScope
public class ShuoServiceImpl implements ShuoService {

  @Value("${path.url.site}")
  private String siteUrl;

  @Value("${path.url.shuo-details}")
  private String shuoDetailPath;

  @Value("${path.folder.shuo}")
  private String shuoPath;

  private final ShuoMapper shuoMapper;

  private final TrackMapper trackMapper;

  private final HomeMapper homeMapper;

  private final CommentService commentService;

  public ShuoServiceImpl(ShuoMapper shuoMapper, TrackMapper trackMapper, HomeMapper homeMapper, CommentService commentService) {
    this.shuoMapper = shuoMapper;
    this.trackMapper = trackMapper;
    this.homeMapper = homeMapper;
    this.commentService = commentService;
  }

  @Override
  @Transactional
  public void addShuoshuo(ShuoAddRequest shuoAddRequest) throws CustomException {
    if (!shuoMapper.addShuoshuo(shuoAddRequest)) {
      throw new CustomException("插入说说失败！");
    }

    if (!shuoAddRequest.getPicList().isEmpty()) {
      Integer insertShuoshuoPics = shuoMapper.addShuoshuoPics(shuoAddRequest.getPicList(), shuoAddRequest.getId());
      if (!insertShuoshuoPics.equals(shuoAddRequest.getPicList().size())) {
        throw new CustomException("插入说说图片失败！");
      }
    }

    if (!homeMapper.insertHome(
        HomeTypeEnum.SHUOSHUO.getType(),
        shuoAddRequest.getId(),
        shuoAddRequest.getStatus())
    ) { throw new CustomException("说说插入home失败！"); }
  }

  @Override
  public FileUploadVO uploadShuoshuoPic(MultipartFile multipartFile) throws CustomException {
    String originalFilename = multipartFile.getOriginalFilename();
    if (Objects.isNull(originalFilename)) {
      throw new CustomException("文件错误");
    }
    String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
    if (!MediaConstant.imgContain(fileType)) {
      throw new CustomException("图片格式不支持");
    }

    String url = FileUtils.sUpload(multipartFile, shuoPath);
    // 记录文件上传日志
    if (!trackMapper.addFileLog(
        FileOperationLogConstant.SHUO,
        UserInfoHolder.getUid(),
        FileMethodEnum.UPLOAD.getMethod(),
        url
    )) { log.error("Failed to record shuo pic upload log"); }

    return new FileUploadVO(url);
  }

  @Override
  public ShuoListInfoVO getShuoshuoListInfo() {
    String role = UserInfoHolder.getRole();

    ShuoDataPO shuoDataPO = shuoMapper.getShuoshuoData(role);
    if (Objects.isNull(shuoDataPO)) {
      return new ShuoListInfoVO();
    }
    if (AuthConfigConstant.ROLE_ADMIN.equals(role)) {
      return new ShuoListInfoVO(
          shuoMapper.getShuoshuoListInfo(null),
          shuoMapper.getShuoshuoListInfo(StatusEnum.PUBLIC.getStatus()),
          shuoMapper.getShuoshuoListInfo(StatusEnum.PRIVATE.getStatus()),
          shuoMapper.getShuoshuoListInfo(StatusEnum.DELETE.getStatus()),
          shuoDataPO.getViewsNum(),
          shuoDataPO.getCommentsNum()
      );
    } else {
      return new ShuoListInfoVO(
          shuoMapper.getShuoshuoListInfo(StatusEnum.PUBLIC.getStatus()),
          shuoDataPO.getViewsNum(),
          shuoDataPO.getCommentsNum()
      );
    }
  }

  @Override
  @Transactional
  public List<ShuoListVO> getShuoshuoList(Integer page, Integer pageSize) throws CustomException {
    String role = UserInfoHolder.getRole();

    List<ShuoshuoInfoDTO> shuoshuoInfoDTOS = shuoMapper.getShuoshuoList((page - 1) * pageSize, pageSize, role);
    List<ShuoListVO> shuoListVOS = new ArrayList<>();
    try {
      for (ShuoshuoInfoDTO item : shuoshuoInfoDTOS) {
        item.setPicList(shuoMapper.getShuoshuoPics(item.getId()));
        ShuoListVO shuoListVO = ShuoConvert.INSTANCE.shuoshuoListPo2Dto(item);
        shuoListVO.setId(SecurityUtils.symmetricEncrypt(item.getId().toString()));
        shuoListVOS.add(shuoListVO);
      }
    } catch (Exception e) {
      throw new CustomException("程序异常");
    }
    return shuoListVOS;
  }

  @Override
  public ShuoListVO getShuoshuoInfo(Integer id) throws CustomException {
//        TODO: status为null的情形是什么
    ShuoshuoInfoDTO shuoshuoInfoDto = shuoMapper.getShuoshuoInfo(id);
      if (Objects.isNull(shuoshuoInfoDto)) {
          throw new CustomException("说说不存在");
      }
    shuoshuoInfoDto.setPicList(shuoMapper.getShuoshuoPics(id));
    ShuoListVO shuoListVO = ShuoConvert.INSTANCE.shuoshuoListPo2Dto(shuoshuoInfoDto);
    try {
      shuoListVO.setId(SecurityUtils.symmetricEncrypt(shuoshuoInfoDto.getId().toString()));
    } catch (Exception e) {
      throw new CustomException("程序异常");
    }

    if (!shuoMapper.addView(id)) {
      throw new CustomException("博客阅读量更新失败");
    }

    return shuoListVO;
  }

  @Override
  public ShuoListVO getShuoshuoInfoByString(String id) throws CustomException {
    String role = UserInfoHolder.getRole();

    String originalId;
    try {
      originalId = SecurityUtils.symmetricDecrypt(id);
    } catch (Exception e) {
      throw new CustomException("错误id");
    }
    ShuoListVO shuoshuoInfo = getShuoshuoInfo(Integer.valueOf(originalId));

    if (!StatusEnum.PUBLIC.getStatus().equals(shuoshuoInfo.getStatus())) {
      if (AuthConfigConstant.ROLE_ADMIN.equals(role)) {
        return shuoshuoInfo;
      }
      throw new CustomException("说说不存在");
    }
    return shuoshuoInfo;
  }

  @Override
  @Transactional
  public void doShuoComment(ShuoCommentAddRequest shuoCommentAddRequest) throws CustomException {
    String role = UserInfoHolder.getRole();
    Long uid = UserInfoHolder.getUid();

    CommentAddRequest commentAddRequest = ShuoConvert.INSTANCE.shuoshuo2Comment(shuoCommentAddRequest);
    try {
      String sid = SecurityUtils.symmetricDecrypt(shuoCommentAddRequest.getObjectId());
      commentAddRequest.setObjectId(Integer.valueOf(sid));
    } catch (Exception e) {
      throw new CustomException("程序错误");
    }

    Integer shuoshuoStatus = shuoMapper.getShuoshuoStatus(commentAddRequest.getObjectId());
    if (
        Objects.isNull(shuoshuoStatus) ||
        (AuthConfigConstant.ROLE_USER.equals(role) && !shuoshuoStatus.equals(StatusEnum.PUBLIC.getStatus())) ||
        (AuthConfigConstant.ROLE_ADMIN.equals(role) && shuoshuoStatus.equals(StatusEnum.DELETE.getStatus()))
    ) { throw new CustomException("说说不存在"); }

    commentAddRequest.setUid(uid);
    commentAddRequest.setUrl(siteUrl + shuoDetailPath + shuoCommentAddRequest.getObjectId().replace("+", "%2B"));
    commentService.publishComment(commentAddRequest, CommentEnum.SHUOSHUO.getType(), true);

    if (!shuoMapper.updateShuoshuoComments(commentAddRequest.getObjectId(), 1)) {
      throw new CustomException("评论数据更新失败");
    }
  }

  @Override
  public List<CommentListVO> getShuoCommentList(String id, Integer page, Integer pageSize) throws CustomException {
    String role = UserInfoHolder.getRole();
    Long uid = UserInfoHolder.getUid();

    Integer originalId;
    try {
      String s = SecurityUtils.symmetricDecrypt(id);
      originalId = Integer.valueOf(s);
    } catch (Exception e) {
      throw new CustomException("程序错误");
    }

    Integer shuoshuoStatus = shuoMapper.getShuoshuoStatus(originalId);
    if (
        Objects.isNull(shuoshuoStatus) ||
            (AuthConfigConstant.ROLE_USER.equals(role) && !shuoshuoStatus
                .equals(StatusEnum.PUBLIC.getStatus())) ||
            (AuthConfigConstant.ROLE_ADMIN.equals(role) && shuoshuoStatus
                .equals(StatusEnum.DELETE.getStatus()))
    ) {
      throw new CustomException("说说不存在");
    }

    return commentService
        .getCommentList(originalId, CommentEnum.SHUOSHUO.getType(), uid, page, pageSize);
  }

  @Override
  public void delShuoComment(Integer id) throws CustomException {
    String role = UserInfoHolder.getRole();
    Long uid = UserInfoHolder.getUid();

    // 可行性检查
    CommentDelInfoPO commentDelInfoPo = shuoMapper.getShuoshuoCommentInfo(id);
    if (
        Objects.isNull(commentDelInfoPo) ||
            !CommentEnum.SHUOSHUO.getType().equals(commentDelInfoPo.getType()) ||
            (AuthConfigConstant.ROLE_USER.equals(role) && !StatusEnum.PUBLIC.getStatus()
                .equals(commentDelInfoPo.getStatus())) ||
            (AuthConfigConstant.ROLE_ADMIN.equals(role) && StatusEnum.DELETE.getStatus()
                .equals(commentDelInfoPo.getStatus()))
    ) {
      throw new CustomException("说说不存在");
    }

    commentService.delComment(id, uid);

    // 更新说说
    if (!shuoMapper.updateShuoshuoComments(commentDelInfoPo.getObjectId(), -1)) {
      throw new CustomException("评论数据更新失败");
    }
  }
}
