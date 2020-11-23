package com.spring.board.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Repository
public class BoardDao {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.spring.board.boardMapper";
 
    //�Խ��� ��� ��ȸ
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardList");
    }
    
    //�Խñ� ��ȸ�� 
    public int updateBoardHits(BoardForm boardForm) throws Exception {
    	return sqlSession.update(NAMESPACE + ".updateBoardHits", boardForm);
    }
    
    //�Խñ� �� ��ȸ
    public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
    	return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardForm);
    }

    //게시글 그룹 번호 조회
	public int getBoardReRef(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardReRef", boardForm);
	}

	//게시글 등록
	public int insertBoard(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoard", boardForm);
	}
	
	//게시글 등록 실패
	public int insertBoardFail(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardFail", boardForm);
	}
	
	//게시글 수정
	public int updateBoard(BoardForm boardForm) {
		return sqlSession.update(NAMESPACE + ".updateBoard", boardForm);
	}

	//게시글 삭제
	public int deleteBoard(BoardForm boardForm) {
		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}
}

