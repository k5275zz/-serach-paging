package org.jht.service;

import java.util.ArrayList;

import org.jht.dto.BoardDTO;
import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface Board2Service {
	// *추상메소드*
	// 게시판 글쓰기
	public void boardWrite(BoardDTO bdto);
	// 게시판 리스트
	public ArrayList<BoardDTO> boardList(PageConditionDTO pcdto);
	// 게시판 상세 페이지
	public BoardDTO boardDetail(@RequestParam("bno") int bno);
	// 게시판 글 수정
	public void boardUpdate(BoardDTO bdto);
	// 게시판 글 삭제
	public void boardDelete(@RequestParam("bno") int bno);
	// 로그인 확인
	public MemberDTO boardlogin(@RequestParam("id") String id);
	// 게시물 총수
	public int getTotal();
}
