package org.jht.service;

import java.util.ArrayList;

import org.jht.dto.BoardDTO;
import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.jht.mapper.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class Board2ServiceImpl implements Board2Service{
	@Autowired
	BoardDAO bdao;
	
	public void boardWrite(BoardDTO bdto) {
		bdao.boardInsert(bdto);
		
	}

	public ArrayList<BoardDTO> boardList(PageConditionDTO pcdto) {
		
		return bdao.boardList(pcdto);
	}


	public BoardDTO boardDetail(@RequestParam("bno") int bno) {

		return bdao.boardDetail(bno);
	}


	public void boardUpdate(BoardDTO bdto) {

		bdao.boardUpdate(bdto);
	}


	public void boardDelete(@RequestParam("bno") int bno) {

		bdao.boardDelete(bno);
	}

	public MemberDTO boardlogin(String id) {
		
		return bdao.boardLoginCheck(id); 
	}

	// 게시물 총수
	public int getTotal() {		
		return bdao.getTotal();
	}

}
