package com.spring.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Service
public class BoardService {
 
	protected final Logger logger = LoggerFactory.getLogger(BoardService.class);

    @Autowired
    private BoardDao boardDao;
 
    //게시글 목록 조회
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return boardDao.getBoardList(boardForm);
    }
    
    //게시글 상세 조회
    public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
    	logger.debug("========== getBoardDetail START ==========");
    	BoardDto boardDto = new BoardDto();
    	String searchType = boardForm.getSearch_type();
    	if("S".equals(searchType))	{
    		boardDao.updateBoardHits(boardForm);
    	}
    	boardDto = boardDao.getBoardDetail(boardForm);
    	logger.debug("========== getBoardDetail END ==========");
    	
    	return boardDto;
    }
    
    //게시글 등록
    public BoardDto insertBoard(BoardForm boardForm) throws Exception{
    	BoardDto boardDto = new BoardDto();
    	int insertCnt = 0;
    	
    	int boardReRef = boardDao.getBoardReRef(boardForm);
    	boardForm.setBoard_re_ref(boardReRef);
    	
    	insertCnt = boardDao.insertBoard(boardForm);
    	System.out.println("insertCnt="+insertCnt);
    	if(insertCnt>0) {
    		boardDto.setResult("SUCCESS");
    	}else {
    		boardDto.setResult("FAIL");
    	}
    	return boardDto;
    }

    //게시글 수정
	public BoardDto updateBoard(BoardForm boardForm) {
		logger.debug("========== updateBoard START ==========");
		BoardDto boardDto = new BoardDto();
		int updateCnt = boardDao.updateBoard(boardForm);
		if(updateCnt>0) {
			boardDto.setResult("SUCCESS");
		}else {
			boardDto.setResult("FAIL");
		}
		logger.debug("========== updateBoard END ==========");
		return boardDto;
	}

	//게시글 삭제
	public BoardDto deleteBoard(BoardForm boardForm) {
		logger.debug("========== deleteBoard START ==========");
		BoardDto boardDto = new BoardDto();
		int deleteCnt = boardDao.deleteBoard(boardForm);
		if(deleteCnt>0) {
			boardDto.setResult("SUCCESS");
		}else {
			boardDto.setResult("FAIL");
		}
		logger.debug("========== deleteBoard END ==========");
		return boardDto;
	}
}
