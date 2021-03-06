package com.kosta.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.vo3.WebBoard;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

	//1. default: findAll(), findById(id값)
	//2. patttern에 맞는 함수정의: findBy속성()..
	List<WebBoard> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	//3. JPQL: @Query, nativeQuery=true
	//4. Querydsl 동적 SQL문장 생성 가능
	List<WebBoard> findAll(Sort by);
}
