package org.jht.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jht.dto.BoardDTO;
import org.jht.dto.MemberDTO;
import org.jht.dto.PageConditionDTO;
import org.jht.dto.PageDTO;
import org.jht.service.Board2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("id")

@RequestMapping("/board")
public class Board2Controller {
	private static final Logger logger = LoggerFactory.getLogger(Board2Controller.class);
	
	@Autowired
	private Board2Service b2service;
	// GET방식 write 페이지
	@RequestMapping(value = "/write", method = RequestMethod.GET, produces="text/plain; charset=UTF-8")
	public String aaa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("id");
			        
		System.out.println("==============================");
		System.out.println("세션에 저장 되 있는 변수 : "+name);
		System.out.println("==============================");
			    
		if(name!=null) {
			return "/board/write";
		}else {
			PrintWriter script = response.getWriter();
			response.setHeader("Content-Type", "text/html;charset=utf-8");
			script.println("<script>");
			script.println("alert('Login Please~!');");
			script.println("location.href = '/login/login'");//이전 화면으로 이동
			script.println("</script>");
			script.close();
			return "/login/login";
		}
		
	}
	// POST방식 write 페이지
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String bbb(BoardDTO bdto) {
		logger.info("title="+bdto.getTitle());
		logger.info("id="+bdto.getWriter());
		logger.info("content="+bdto.getContent());
		b2service.boardWrite(bdto);
		return "redirect:/board/list";
	}
	// list
	@GetMapping(value = "/list")
	/* 페이징하기전 리스트
	 * public void Getlist(Model model) { //
	 * b2service.boardList().forEach(board->logger.info(""+board)); // 제대로 값이 들어왔는지
	 * 찍어보는 용도 ArrayList<BoardDTO> list = b2service.boardList();
	 * //model.addAttribute("loginlist", id); model.addAttribute("list", list); //
	 * forward방식으로 보낸다. }
	 */
	//페이징 후 리스트
	public void getListPaging(PageConditionDTO pcdto,Model model) {
		logger.info("type="+pcdto.getType());
		int total=b2service.getTotal();
		logger.info("pcdto="+pcdto);
		logger.info("전체 건수="+total);
		model.addAttribute("list", b2service.boardList(pcdto));
		model.addAttribute("pageMaker",new PageDTO(pcdto,total));
	}
	
	// 로그인 확인후 리스트페이지로 이동
	@PostMapping(value = "/list")
	public String Postlist(@RequestParam("id") String id,@RequestParam("pw") String pw, Model model) {
		MemberDTO mdto = b2service.boardlogin(id);
		if(mdto!=null && mdto.getPw().equals(pw)) {
			model.addAttribute("loginlist", b2service.boardlogin(id));
			model.addAttribute("id", id);
			return "redirect:/board/list";
		}else {
			return "/login/login";
		}
		
		//model.addAttribute("loginlist", b2service.boardlogin(id));
		
	}
	// 상세 페이지
	@GetMapping(value = "/detail")
	public void GetDetail(@RequestParam("bno") int bno, Model model) {
		model.addAttribute("detail", b2service.boardDetail(bno));
	}
	// 수정 페이지
	@GetMapping(value = "/update")
	public void GetModify(@RequestParam("bno") int bno, Model model)
	{
		/*
		 * HttpSession session = request.getSession(); String name = (String)
		 * session.getAttribute("id"); if(name.equals(writer)) {
		 * model.addAttribute("detail", b2service.boardDetail(bno)); }else {
		 * 
		 * }
		 */
		model.addAttribute("detail", b2service.boardDetail(bno));
		
	}
	
	// 수정 로직실행 페이지
	@PostMapping(value = "/update")
	public String PostModify(BoardDTO bdto) {
		b2service.boardUpdate(bdto);
		return "redirect:/board/list";
	}
	// 삭제 페이지
	@GetMapping(value = "/delete")
	public String GetDelete(@RequestParam("bno") int bno) {
		b2service.boardDelete(bno);
		return "redirect:/board/list";
	}
	
}
