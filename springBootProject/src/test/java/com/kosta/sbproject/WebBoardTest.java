package com.kosta.sbproject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.WebBoardRepository;
import com.kosta.vo3.WebBoard;

@SpringBootTest
public class WebBoardTest {

	@Autowired
	WebBoardRepository webRepo;
	
	@Test
	public void countAll() {
		System.out.println(webRepo.count());
	}
	
	//@Test
	public void selectAll() {
		webRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	//300건의 board insert
	//@Test
	public void insertBoard() {
		IntStream.rangeClosed(0, 300).forEach(i->{
			WebBoard board = WebBoard.builder().title("sample board title " + i).content("board내용 " + i).writer("user0" + i%10).build();
			webRepo.save(board);
		});
	}
}
