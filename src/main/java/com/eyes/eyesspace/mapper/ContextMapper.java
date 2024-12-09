package com.eyes.eyesspace.mapper;

import com.eyes.eyesspace.model.po.ContextPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContextMapper {
	@Select("<script>"
			+ "select name, value from context "
			+ "where id in "
			+ "<foreach collection='ids' item='item' index='index' open='(' separator=',' close=')'>"
			+ "#{item}"
			+ "</foreach>"
			+ "</script>")
	List<ContextPO> getContext(List<Integer> ids);
}