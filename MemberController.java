package org.jht.controller;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.jht.dto.PageDTO;
import org.jht.dto.TodoDTO;
import org.jht.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService mbsv;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// member/ write.jsp �뤌�쑝濡� �씠�룞�븯湲� �쐞�븳 controller
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void Getwrite() {
		logger.info("write");
		
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public void Postwrite(MemberDTO mdto) {
		mbsv.memberJoin(mdto);
		//return "redirect:/member/list";
	}
	
	@GetMapping(value = "/list")
	public void GetList(PageConditionDTO pcdto,Model model) {
		int total=mbsv.getTotal();
		ArrayList<MemberDTO> list = mbsv.memberList(pcdto);
		model.addAttribute("pageMaker",new PageDTO(pcdto,total));
		model.addAttribute("list", list);
		logger.info("pcdto="+pcdto);
		logger.info("total="+total);
	}
	
	@GetMapping(value = "/detail")
	public void GetDetail(@RequestParam("id") String id,Model model ) {
		model.addAttribute("detail", mbsv.memberDetail(id));
	}
	@GetMapping(value = "/update")
	public void GetModify(@RequestParam("id") String id, Model model) {
		// detail과 같은 로직사용
		model.addAttribute("update", mbsv.memberDetail(id));
		
	}
	
	@PostMapping(value = "/modify") 
	public String PostModify(MemberDTO mdto) {
	  mbsv.memberModify(mdto); 
	  return "redirect:/member/list";
	}
	
	@PostMapping(value = "/delete")
	public String PostDelete(@RequestParam("id") String id) {
		logger.info("id="+id);
		mbsv.memberDelete(id);
		return "redirect:/member/list";
	}
}
