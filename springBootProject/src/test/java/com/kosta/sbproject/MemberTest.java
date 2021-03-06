package com.kosta.sbproject;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.MemberRepository;
import com.kosta.vo.MemberVO;

@SpringBootTest
public class MemberTest {

	@Autowired
	MemberRepository memberRepo;

	//@Test
	public void memberInsert() {
		IntStream.range(1, 11).forEach(index -> {
			MemberVO m = MemberVO.builder()
					.mpassword("1234")
					.address("seoul" + index)
					.mname("멤버" + index)
					.phone("010-1234-123" + index)
					.email("zz" + index + "@naver.com").build();
			memberRepo.save(m);
		});
	}

	@Test
	public void selectAll() {
		List<MemberVO> mlist = (List<MemberVO>) memberRepo.findAll();
		mlist.forEach(member -> {
			System.out.println(member);
		});
	}

}
