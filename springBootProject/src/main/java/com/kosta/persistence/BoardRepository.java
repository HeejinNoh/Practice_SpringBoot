package com.kosta.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kosta.vo.BoardVO;

// 인터페이스 설계. 패키지는 groupid.artifactid에 있거나 하위에 있을 것
//CrudRepository<BoardVO, Long> <-- PagingAndSortingRepository<BoardVO, Long> 상속했기에 crud빼도됨
@Repository
public interface BoardRepository extends PagingAndSortingRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO>{
	
	//findBy필드이름: 규격 꼭 지키기
	List<BoardVO> findByTitle(String title);
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByContent(String content);
	
	List<BoardVO> findByTitleAndWriter(String title, String writer);
	List<BoardVO> findByTitleOrContent(String title, String content);
	
	long countByWriter(String writer);
	
	List<BoardVO> findByTitleLike(String title); //여러건이니까 list
	List<BoardVO> findByTitleContaining(String title);
	List<BoardVO> findByTitleStartingWith(String title);
	List<BoardVO> findByTitleEndingWith(String title);
	
	List<BoardVO> findByTitleEndingWithAndBnoGreaterThanEqual(String title, Long bno);
	List<BoardVO> findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc(String title, Long bno);
	
	//paging and sort기능
	List<BoardVO> findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc
	(String title, Long bno, Pageable paging); //domain 임포트.. 오버로딩됨
	
//	List<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
	
	Page<BoardVO> findByBnoGreaterThan(Long bno, Pageable paging);
	long countByBnoGreaterThan(long bno);
	
	//JPQL(JPA Query Language).. SQL복잡, 패턴에 맞는 함수정의 불가한 경우 이용 3가지 경우!
	@Query("select b from BoardVO b where b.title like %?1% and b.bno > ?2 order by b.bno")
	public List<BoardVO> findByTitle2(String title, Long bno); //?1번째, ?2번째 파라메터
	
	@Query("select b from BoardVO b where b.title like %:tit% and b.bno > :bno order by b.bno")
	public List<BoardVO> findByTitle3(@Param("tit") String title, @Param("bno")Long bno);
	
	@Query("select b from #{#entityName} b where b.title like %:tit% and b.bno > :bno order by b.bno")
	public List<BoardVO> findByTitle4(@Param("tit") String title, @Param("bno")Long bno);
	
	//DB의 SQL문장
	@Query(value = "select * from tbl_boards b where b.title like %?1% and b.bno > ?2 order by b.bno", nativeQuery = true)
	public List<BoardVO> findByTitle5(String title, Long bno);
	
	@Query(value = "select bno, substr(title, 0, 3), concat(writer, '님') from tbl_boards b where b.title like %?1% and b.bno > ?2 order by b.bno", nativeQuery = true)
//	public List<BoardVO> findByTitle6(String title, Long bno); // select 요소와 BoardVO의 받는 구조가 다름 > 오류: 부적합한 열 이름
	public List<Object[]> findByTitle6(String title, Long bno); //3건인지 4건인지 모름.. 배열로 받자!
	
	@Query("select board from #{#entityName} board order by board.bno")
	public Page<BoardVO> findByPage(Pageable paging);
}
