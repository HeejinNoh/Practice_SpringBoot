package com.kosta.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.vo2.MemberDTO;
import com.kosta.vo2.ProfileDTO;

public interface ProfileRepository extends PagingAndSortingRepository<ProfileDTO, Long>{

	//1. default method : findAll(), findById()
	//2. Method추가 (규칙에 맞도록 함수이름 선언)
	List<ProfileDTO> findByPname(String pname);
	List<ProfileDTO> findByCurrentYn(boolean yn);
	List<ProfileDTO> findByMember(MemberDTO member);
	
	//3. JPQL(JPA Query Language)
	
	//DB에서 사용하는 SQL문 : nativeQuery = true
	@Query(value="select tbl_members.MID, count(tbl_profile.pno) from tbl_profile"
			+ "	right outer join tbl_members on(tbl_profile.MEMBER_MID = tbl_members.MID)"
			+ "	group by tbl_members.MID order by 1", nativeQuery = true)
	List<Object[]> selectByMemberProfileCount();
	
	//JPQL --sql문과 비슷
	@Query(value="select m.mid, count(p) from ProfileDTO p"
			+ "	right outer join MemberDTO m on(p.member = m.mid)"
			+ "	group by m.mid order by 1")
	List<Object[]> selectByMemberProfileCount2();
	
	@Query(value="select m, p from ProfileDTO p"
			+ "	right outer join MemberDTO m on(p.member = m.mid)"
			+ "	where m.mid =?1 and p.currentYn =0 order by m.mid")
	List<Object[]> selectByMemberProfile(String mid);
	
}