package com.eyes.eyesspace.service;

import com.eyes.eyesspace.common.exception.CustomException;
import com.eyes.eyesspace.common.result.PageBind;
import com.eyes.eyesspace.model.vo.BookInfoVO;
import com.eyes.eyesspace.model.vo.BookListInfoVO;
import com.eyes.eyesspace.model.vo.BookListVO;

/**
 * @author eyesYeager
 * data 2024/12/9 20:52
 */

public interface BookService {
	BookListInfoVO getBookListInfo();

	PageBind<BookListVO> getBookList(Integer page);

	BookInfoVO getBookInfo(Integer id) throws CustomException;
}
