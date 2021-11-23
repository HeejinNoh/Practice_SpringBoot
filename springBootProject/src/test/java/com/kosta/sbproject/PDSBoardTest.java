package com.kosta.sbproject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.PDSBoardRepository;
import com.kosta.persistence.PDSFileRepository;
import com.kosta.vo2.PDSBoard;
import com.kosta.vo2.PDSFile;

@SpringBootTest
public class PDSBoardTest {

	@Autowired
	PDSBoardRepository boardRepo;
	
	@Autowired
	PDSFileRepository fileRepo;
	
	//Board지우면 File도 지워짐
	@Test
	public void bardDeleteFileDelete() {
		boardRepo.findById(23L).ifPresent(board->{
			boardRepo.delete(board);
		});
	}
	
	
	//board수정, file추가
	//@Test
	public void boardUpdateFileAdd() {
		boardRepo.findById(23L).ifPresent(board->{
			board.setPname("23번 board를 수정함");
			List<PDSFile> flist = board.getFiles2(); //5건
			PDSFile file = new PDSFile();
			file.setPdsfilename("23번 board의 file");
			flist.add(file);
			PDSFile file2 = new PDSFile();
			file2.setPdsfilename("23번 board의 file2");
			flist.add(file2);
			boardRepo.save(board);
		});
	}
	
	//@Test
	public void selectById() {
		boardRepo.findById(21L).ifPresentOrElse(b->{
			System.out.println(b);
		}, ()->{
			System.out.println("해당 데이터를 찾지 못했습니다.");
		});
	}
	
	//@Test
	public void selectAllBoard() {
		boardRepo.findAll().forEach(b->{
			System.out.println(b);
		});
	}
	
	//@Test
	public void selectByFileId() {
		fileRepo.findById(25L).ifPresent(f->{
			System.out.println(f);
		});
	}
	
	//@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 3).forEach(i->{
			PDSBoard board = new PDSBoard();
			board.setPname("filename" + i);
			board.setPwriter("작성자" + i);
			List<PDSFile> files2 = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j->{
				PDSFile file = new PDSFile();
				file.setPdsfilename("flower" + i + "::" + j);
				files2.add(file);
			});
			board.setFiles2(files2);
			boardRepo.save(board);
		});
	}
}
