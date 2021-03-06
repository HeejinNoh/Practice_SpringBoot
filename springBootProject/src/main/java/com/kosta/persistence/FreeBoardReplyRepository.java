package com.kosta.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kosta.vo2.FreeBoard;
import com.kosta.vo2.FreeBoardReply;

public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long>{

	// 패턴에 맞게 함수 정의
	List<FreeBoardReply> findByBoard(FreeBoard board);
}
