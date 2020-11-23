package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
 
    @Autowired
    private BoardService boardService;
    
    //게시글 목록 페이지 이동
    @RequestMapping( value = "/boardList")
    public String getBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "board/boardList";
    }
 
    //게시글 목록 조회
    @RequestMapping(value = "/getBoardList")
    @ResponseBody
    public List<BoardDto> getBoardList(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
 
        List<BoardDto> boardList = boardService.getBoardList(boardForm);
    	
        return boardList;
    }
    
    //게시글 상세 페이지 이동
    @RequestMapping(value = "/boardDetail")
    public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
    	return "board/boardDetail";
    }
    
    //게시글 상세페이지 조회
    @RequestMapping(value = "/getBoardDetail")
    @ResponseBody
    public BoardDto getBoardDetail(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
    	MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
    	BoardDto boardDto = boardService.getBoardDetail(boardForm);
    	MDC.remove("TRANSACTION_ID");
    	return boardDto;
    }
    
    //게시글 작성 페이지 이동
    @RequestMapping(value="/boardWrite")
    public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "board/boardWrite";
    }
    
    //게시글 등록
    @RequestMapping(value="/insertBoard")
    @ResponseBody
    public BoardDto insertBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
    	MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
    	BoardDto boardDto = boardService.insertBoard(boardForm);
    	return boardDto;
    }
    
    //게시글 수정 페이지 이동
    @RequestMapping(value="/boardUpdate")
    public String boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "board/boardUpdate";
    }
    
    //게시글 수정
    @RequestMapping(value="/updateBoard")
    @ResponseBody
    public BoardDto updateBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
    	MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		BoardDto boardDto = boardService.updateBoard(boardForm);
		MDC.remove("TRANSACTION_ID");
    	return boardDto;
    }
    
    //게시글 삭제
    @RequestMapping(value="/deleteBoard")
    @ResponseBody
    public BoardDto deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {
    	MDC.put("TRANSACTION_ID", String.valueOf(boardForm.getBoard_seq()));
		BoardDto boardDto = boardService.deleteBoard(boardForm);
		MDC.remove("TRANSACTION_ID");
    	return boardDto;
    }
}

