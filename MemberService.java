package org.jht.service;

import java.util.ArrayList;

import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberService {
	// �쉶�썝媛��엯
	public void memberJoin(MemberDTO mdto);
	// �쉶�썝 由ъ뒪�듃 紐낅떒
	public ArrayList<MemberDTO> memberList(PageConditionDTO pcdto);
	// �쉶�썝 �긽�꽭 �젙蹂�
	public MemberDTO memberDetail(@RequestParam("id") String id);
	// �쉶�썝 �젙蹂� �닔�젙
	public void memberModify(MemberDTO mdto); 
	// �쉶�썝 �깉�눜
	public void memberDelete(@RequestParam("id") String id);
	
	public int getTotal();
}
