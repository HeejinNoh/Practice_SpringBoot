package com.kosta.sbproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.FreeBoardReplyRepository;
import com.kosta.persistence.FreeBoardRepository;
import com.kosta.vo2.FreeBoard;
import com.kosta.vo2.FreeBoardReply;

@SpringBootTest
public class FreeBoardReplyTest {

	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	//@Test
	public void replySelectById() {
		FreeBoard board = boardRepo.findById(40L).get();
		List<FreeBoardReply> replies = replyRepo.findByBoard(board);
		replies.forEach(reply -> {
			System.out.println(reply);
			System.out.println(reply.getBoard());
			System.out.println("-------");
		});
	}
	
	//@Test
	public void replyInsert() {
		FreeBoard board = boardRepo.findById(40L).get();
		IntStream.range(200, 205).forEach(i->{
			FreeBoardReply reply = FreeBoardReply.builder().reply("댓글insert").replyer("user40").board(board).build();
			replyRepo.save(reply); //댓글로 세이브
		});
	}
	
	//특정Board수정(bno) > board수정, 댓글추가
	//특정Board삭제(bno)
	//@Test
	public void updateDeleteBoard() {
		boardRepo.findById(33L).ifPresentOrElse(board->{
			board.setContent("오늘의 날씨는 영하입니다.");
			FreeBoardReply reply = FreeBoardReply.builder().reply("댓글추가완료!").replyer("user33").board(board).build();
			board.getReplies().add(reply);
			boardRepo.save(board);
		}, ()->{
			System.out.println("해당board없음");
		});
		
//		boardRepo.deleteById(43L);
		boardRepo.findById(43L).ifPresentOrElse(board->{
			boardRepo.delete(board);
		}, ()->{
			System.out.println("해당board없음");
		});
		
	}
	
	//Board의 정보 살펴보기 ... Board의 댓글 수
	@Test @Transactional
	public void selectAllBoard() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board); //tostring 호출됨 > StackOverflowError
			System.out.println(board.getReplies().size() + "건 댓글");
			for(FreeBoardReply reply : board.getReplies()) {
				System.out.println(reply);
			}
			System.out.println("------------------");
		});
	}
	
	//@Test
	public void insertReply() {
		LongStream.rangeClosed(33, 43).forEach(bno->{
			boardRepo.findById(bno).ifPresentOrElse(board->{
				List<FreeBoardReply> replyList = board.getReplies();
				IntStream.rangeClosed(1, 5).forEach(i->{
					FreeBoardReply reply = FreeBoardReply.builder().reply("댓글내용" + i + ":" + bno).replyer("댓글작성자" + i + ":" + bno).board(board).build();
					replyList.add(reply);
				});
				boardRepo.save(board);
			}, ()->{System.out.println("해당 Board없음");});
		});
	}
	
	//@Test
	public void insertBoard() {
		IntStream.rangeClosed(100, 110).forEach(j->{
			FreeBoard board = FreeBoard.builder().title("board 제목" + j).writer("board 작성자" + j).content("board 내용" + j).build();
			boardRepo.save(board);
		});
	}
}
