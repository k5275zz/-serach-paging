package org.jht.service;

import java.util.ArrayList;

import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.jht.mapper.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO mdao;
	
	public void memberJoin(MemberDTO mdto) {
		mdao.memberInsert(mdto);
	}

	public ArrayList<MemberDTO> memberList(PageConditionDTO pcdto) {
		return mdao.memberList(pcdto);
	}

	public MemberDTO memberDetail(@RequestParam("id") String id) {
		
		return mdao.memberDetail(id);
	}

	
	public void memberModify(MemberDTO mdto) {
		
		mdao.memberUpdate(mdto); 
	}

	public void memberDelete(String id) {

		mdao.memberDelete(id);
	}


	public int getTotal() {

		return mdao.getTotal();
	}
	
	
}
