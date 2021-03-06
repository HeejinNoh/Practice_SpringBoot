package com.kosta.sbproject;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.BoardRepository;
import com.kosta.vo.BoardVO;

@SpringBootTest
public class BoardTest {

	@Autowired
	BoardRepository boardRepo;
	
	//@Test
	public void boardDelete() {
		boardRepo.deleteById(1L);
		long result = boardRepo.count();
		System.out.println(result + "건");
	}
	
	//@Test
	public void boardUpdate() {
		boardRepo.findById(1L).ifPresent(b->{ //존재한다면
			System.out.println("1건 읽기: " + b);
			b.setTitle("title수정");
			b.setContent("내용수정");
			boardRepo.save(b);
		});
		boardRepo.findById(1L).ifPresent(b->{ //존재한다면
			System.out.println("수정한 1건 조회: " + b);
		});
	}
	
	//@Test
	public void selectById() {
		boardRepo.findById(1L).ifPresent(b->{ //존재한다면
			System.out.println("1건 조회: " + b);
		});
	}
	
	/*
	 * select boardvo0_.bno as bno1_0_, 
	 * boardvo0_.content as content2_0_, 
	 * boardvo0_.regdate as regdate3_0_, 
	 * boardvo0_.title as title4_0_, 
	 * boardvo0_.updatedate as updatedate5_0_, 
	 * boardvo0_.writer as writer6_0_ 
	 * from tbl_boards boardvo0_
	 */
	@Test
	public void selectAllTest() {
		boardRepo.findAll().forEach(b->{
			System.out.println("게시물정보: " + b);
		});
	}
	
	//@Test
	public void insertTest() {
		BoardVO b2 = BoardVO.builder().content("SpringBoot배우기2").title("SB2").writer("jin2").build();
		boardRepo.save(b2);
//		builder만 추가했을 때 ()생성자 못함. noargs, allargs 모두 써야함
		BoardVO b1 = new BoardVO();
		b1.setContent("SpringBoot배우기");
		b1.setTitle("SB");
		b1.setWriter("jin");
		boardRepo.save(b1);
	}
	//@Test
	public void test1() {
		Class<?> bclass = boardRepo.getClass(); // <?> 와일드카드, 아무거나와도 다 괜찮다
		System.out.println(bclass.getName());
		Class<?>[] interfs = bclass.getInterfaces(); 
		Stream.of(interfs).forEach(inter->{
			System.out.println(inter.getClass().getName());
			System.out.println(inter.getSuperclass().getName());
		});
	}
}
