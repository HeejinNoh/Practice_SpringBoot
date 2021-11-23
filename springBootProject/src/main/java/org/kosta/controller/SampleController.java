package org.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.persistence.BoardRepository;

@Controller
public class SampleController {

	@Autowired
	BoardRepository brepo;
	
	@RequestMapping("/hello")
	public String test() {
		System.out.println("hello요청");
		return "hello";
	}
	
	@RequestMapping("/hello2")
	public String test2(Model model) {
		System.out.println("hello2요청");
		model.addAttribute("boardlist", brepo.findAll());
		return "boardlist";
	}
}
