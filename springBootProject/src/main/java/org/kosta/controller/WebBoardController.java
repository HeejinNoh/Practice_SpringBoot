package org.kosta.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosta.persistence.WebBoardRepository;
import com.kosta.vo3.WebBoard;

@Controller
public class WebBoardController {

	@Autowired
	WebBoardRepository webRepo;
	
	@RequestMapping("/list")
	public String selectAll(Model model, HttpSession session) {
		session.setAttribute("loginUser", "jin");
		//model.addAttribute("blist", webRepo.findByBnoGreaterThanOrderByBnoDesc(0L));
		model.addAttribute("blist", webRepo.findAll(Sort.by(Sort.Direction.DESC, "bno")));
		return "webBoard/list"; //접미사(templates/)폴더이름/html파일 이름
	}
	 
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String selectById(Long bno, Model model) {
		WebBoard board = webRepo.findById(bno).orElse(null);
		model.addAttribute("board", webRepo.findById(bno).get()); 
		//optional에서 get으로 하면 WebBoard타입으로 변경
		return "webBoard/detail";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(WebBoard board) {
		webRepo.save(board);
		return "redirect:/list"; //수정후 다시 리스트로 돌아가기
	}
	
	@RequestMapping("/register")
	public String insertForm() {
		return "webBoard/register";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(WebBoard board) {
		webRepo.save(board);
		return "redirect:/list"; //등록후 다시 리스트로 돌아가기
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Long bno) {
//		webRepo.findById(bno).ifPresent(board->{
//			webRepo.delete(board);
//		});
		webRepo.deleteById(bno);
		return "redirect:/list";
	}
}
