package com.kosta.sbproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kosta.persistence.BoardRepository;
import com.kosta.vo.BoardVO;
import com.kosta.vo.QBoardVO;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class BoardTest2 {

	// db에 넣고 싶으면 레파지토리가 와야함.
	@Autowired
	BoardRepository brepo;

	@Test
	public void testPredicate() {
		String type = "writer";
		String keyword = "user5";

		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board = QBoardVO.boardVO;
		if (type.equals("content")) {
			builder.and(board.content.like("%" + keyword + "%"));
		} else if (type.equals("writer")) {
			builder.and(board.writer.like("%" + keyword + "%")); //boardVO.writer like %5%
		} else if (type.equals("title")) {
			builder.and(board.title.like("%" + keyword + "%"));
		}
		builder.and(board.bno.gt(100L)); // bno >10
		System.out.println("동적SQL: " + builder);
		System.out.println(builder);
		Pageable pageable = PageRequest.of(0, 5);
		Page<BoardVO> result = brepo.findAll(builder, pageable);
		System.out.println("getSize:" + result.getSize());
		System.out.println("getTotalElements:" + result.getTotalElements());
		System.out.println("getTotalPages:" + result.getTotalPages());
		System.out.println("nextPageable:" + result.nextPageable());
		result.getContent().forEach(evo -> {
			System.out.println(evo);
		});
	}

	// @Test
	public void findByPageTest() {
		Pageable paging = PageRequest.of(0, 5);
		Page<BoardVO> result = brepo.findByPage(paging);
		System.out.println("전체페이지: " + result.getTotalPages());
		// 한페이지 찍기
//		List<BoardVO> blist0 = result.getContent();
//		for(BoardVO b:blist0) {
//			System.out.println(b);
//		}
		// 전체페이지 찍기 **********다시보기25분!
		IntStream.range(0, result.getTotalPages()).forEach(p -> {
			System.out.println((p + 1) + "page정보------");
			Pageable paging2 = PageRequest.of(0, 5);
			Page<BoardVO> result2 = brepo.findByPage(paging2);
			List<BoardVO> blist0 = result2.getContent();
			for (BoardVO b : blist0) {
				System.out.println(b);
			}
		});
	}

	// @Test
	public void findByTitle6NativeQueryTest() {
		brepo.findByTitle6("목9", 100L).forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});
	}

	// @Test
	public void findByTitle5NativeQueryTest() {
		brepo.findByTitle5("목9", 100L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitle4() {
		brepo.findByTitle4("목9", 100L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitle3() {
		brepo.findByTitle3("목9", 100L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitle2() {
		brepo.findByTitle2("목9", 100L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test //서브쿼리진행(sort먼저 > rownum > 부등호)
	public void findByBnoGreaterThan2() { // Page<BoardVO> 전체페이지 다나오게
//		Pageable paging = PageRequest.of(0,  10, Sort.Direction.DESC, "bno");
//		Page<BoardVO> result = brepo.findByBnoGreaterThan(100L, paging); //bno : 100보다 큰수
//		System.out.println("전체 " + result.getTotalPages() + "페이지"); //전체 12페이지
		// 전체건수를 이용해서 페이지계산하기
		int totalPage = (int) Math.ceil(brepo.countByBnoGreaterThan(100L) / 10.0); // 12.5 > 13페이지
		// IntStream.range(inclusive, exclusive) >> +1을 해야하는 이유. 그러나 0페이지부터니까 굳이 +1 하지
		// 않아도됨
		IntStream.range(0, totalPage).forEach(page -> { // +1을 빼버림. 왜 +1하지? //위처럼 할땐 시작이 1페이지
			Pageable paging2 = PageRequest.of(page, 10, Sort.Direction.DESC, "bno");
			Page<BoardVO> result2 = brepo.findByBnoGreaterThan(100L, paging2);
			List<BoardVO> blist = result2.getContent(); // getContent() > List
			blist.forEach(board -> {
				System.out.println(board); // 한페이지만 나옴.PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
			});
			System.out.println((page + 1) + "페이지 끝---------------");
		});
	}

	// @Test
	public void findByBnoGreaterThan() { // Page<BoardVO>
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<BoardVO> result = brepo.findByBnoGreaterThan(100L, paging); // bno : 100보다 큰수
		System.out.println("page size: " + result.getSize()); // 한페이지 10개
		System.out.println("getTotalPages 페이지수: " + result.getTotalPages()); // 페이지수 12
		System.out.println("getTotalElements 건수: " + result.getTotalElements()); // 전체건수 112
		System.out.println("nextPageable: " + result.nextPageable()); // 다음페이지 정보(0페이지 다음 1)

		List<BoardVO> blist = result.getContent(); // getContent() > List
		blist.forEach(board -> {
			System.out.println(board); // 한페이지만 나옴.PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		});
	}

	// @Test
	/*
	 * public void findByBnoGreaterThan() { //List<BoardVO> Pageable paging =
	 * PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
	 * brepo.findByBnoGreaterThan(100L, paging).forEach(board->{
	 * System.out.println(board); });
	 * System.out.println("0page 끝------------------------");
	 * 
	 * paging = PageRequest.of(2, 10, Sort.by("bno").ascending());
	 * brepo.findByBnoGreaterThan(100L, paging).forEach(board->{
	 * System.out.println(board); });
	 * System.out.println("2page 끝------------------------"); //0 > 2페이지.. 1페이지 넘어감
	 * 
	 * }
	 */

	// @Test
	public void descTestPaging() {
		Pageable paging = PageRequest.of(0, 5);
		brepo.findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc("5", 90L, paging).forEach(board -> {
			System.out.println(board);
		});
		System.out.println("0page 끝----------------------------------");

		paging = PageRequest.of(1, 5);
		brepo.findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc("5", 90L, paging).forEach(board -> {
			System.out.println(board);
		});
		System.out.println("1page 끝----------------------------------");
	}

	// @Test
	public void findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc() {
		brepo.findByTitleEndingWithAndBnoGreaterThanEqualOrderByBnoDesc("찾기", 90L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitleEndingWithAndBnoGreaterThanEqual() {
		brepo.findByTitleEndingWithAndBnoGreaterThanEqual("찾기", 90L).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitleEndingWith() {
		brepo.findByTitleEndingWith("30").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitleStartingWith() {
		brepo.findByTitleStartingWith("t").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void findByTitleContaining() {
		brepo.findByTitleContaining("11").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void likeTest() {
		brepo.findByTitleLike("제목1%").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void countTest() {
		System.out.println("전체건수: " + brepo.count()); // select count(*) from tbl_board;
		System.out.println("writer-user7건수: " + brepo.countByWriter("user7")); // select count(*) from tbl_board where
																				// writer='user7';

	}

	// @Test
	public void selectByOrTest() {
		brepo.findByTitleOrContent("title찾기", "오늘은 수능일0").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void selectByAndTest() {
		// select * from tbl_board where title='' and writer='';
		brepo.findByTitleAndWriter("제목107", "user7").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void selectByContent() {
		brepo.findByContent("오늘은 수능일0").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void selectByWriter() {
		brepo.findByWriter("user3").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void updateByIds() {
		Long[] ids = { 88L, 90L, 100L, 44L, 202L }; // update tbl_board set title="title찾기" where bno in (88, 90, 100,
													// 44, 202)
		List<Long> idlist = Arrays.asList(ids); // 반복자로 주기 위해 List<Long> or Iterable<Long>
		brepo.findAllById(idlist).forEach(board -> {
			board.setTitle("title찾기");
			brepo.save(board);
			System.out.println(board);
		});
	}

	// @Test
	public void selectByTitle() {
		brepo.findByTitle("title찾기").forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void selectByIds() {
		Long[] ids = { 88L, 90L, 100L, 44L, 202L }; // where bno in (88, 90, 100, 44, 202)
		List<Long> idlist = Arrays.asList(ids); // 반복자로 주기 위해 List<Long> or Iterable<Long>
		brepo.findAllById(idlist).forEach(board -> {
			System.out.println(board);
		});
	}

	// @Test
	public void selectById() {
		brepo.findById(88L).ifPresent(board -> {
			System.out.println(board);// where bno=88
		});
	}

	// @Test
	public void selectAll() {
		brepo.findAll().forEach(board -> {
			System.out.println(board);
		});
		// List<BoardVO> blist = (List<BoardVO>)brepo.findAll();
	}

	// @Test
	public void insert200() {
		IntStream.range(1, 201).forEach(i -> {
			BoardVO board = BoardVO.builder().title("제목" + i).content("오늘은 수능일" + i % 10).writer("user" + i % 10)
					.build();
			brepo.save(board);
		});
	}
}
