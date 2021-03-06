package org.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.persistence.BoardRepository;
import com.kosta.persistence.DeptRepositoy;
import com.kosta.persistence.FreeBoardRepository;
import com.kosta.vo2.FreeBoard;

@Controller
public class SampleController3 {
	
	@Autowired
	BoardRepository brepo;
	
	@Autowired
	FreeBoardRepository freeRepo;
	
	@RequestMapping("/thyme1")
	public String test1(Model model) { //data를 가져갈 것이면 Model
		model.addAttribute("message", "안녕하세요");
		return "thyme1";
	}
	
	@RequestMapping("/thyme2")
	public void test2(Model model) { //data를 가져갈 것이면 Model
		List<FreeBoard> blist = (List<FreeBoard>) freeRepo.findAll();
		model.addAttribute("boardlist", blist);
		model.addAttribute("message", "success");
		model.addAttribute("result", 100);
	}
	
	@RequestMapping("/thyme3")
	public void test3(Model model) { //data를 가져갈 것이면 Model
		model.addAttribute("board", freeRepo.findById(42L).get()); //.get해야 옵셔널에서 객체로 바뀜
	}

}
