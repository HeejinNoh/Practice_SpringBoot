package com.kosta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.persistence.BoardRepository;
import com.kosta.persistence.FreeBoardRepository;
import com.kosta.persistence.MemberRepository;
import com.kosta.vo.BoardVO;
import com.kosta.vo.CarVO;
import com.kosta.vo.MemberVO;
import com.kosta.vo2.FreeBoard;

@RestController //@Controller + @ResponseBody
public class SampleRestController {

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	FreeBoardRepository freebrepo;
	
	@RequestMapping("/test6") 
	public List<FreeBoard> test6(){
		List<FreeBoard> blist = (List<FreeBoard>)freebrepo.findAll();
		return blist;
	}
	
	@RequestMapping("/test1")
	public String test1() {
		System.out.println("@RestController의 test1()요청");
		return "<h1>@RestController의 test1()요청</h1>";
	}
	
	@RequestMapping("/test2")
	public CarVO test2() {
		CarVO car1 = CarVO.builder().model("ABC").price(1000).build(); //chain방식
		return car1;
	}
	
	@RequestMapping("/test3")
	public List<CarVO> test3() {
		List<CarVO> carlist = new ArrayList<>();
		CarVO car1 = CarVO.builder().model("ABC").price(1000).build(); //chain방식
		carlist.add(car1);
		carlist.add(new CarVO("AAA", 2000));
		CarVO car2 = new CarVO();
		car2.setModel("BBB");
		car2.setPrice(3000);
		carlist.add(car2);
		
		return carlist;
	}
	
	@RequestMapping("/test4")
	public List<MemberVO> test4() {
		List<MemberVO> mlist = (List<MemberVO>) memberRepo.findAll();
		return mlist;
	}
	
	@RequestMapping("/test5")
	public List<BoardVO> test5() {
		List<BoardVO> blist = (List<BoardVO>) boardRepo.findAll();
		return blist;
	}
}
