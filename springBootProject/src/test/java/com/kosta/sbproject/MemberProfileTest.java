package com.kosta.sbproject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.MemberReferenceRepository;
import com.kosta.persistence.ProfileRepository;
import com.kosta.vo2.MemberDTO;
import com.kosta.vo2.MemberRole;
import com.kosta.vo2.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class MemberProfileTest {

	@Autowired
	MemberReferenceRepository memberRepo;
	@Autowired
	ProfileRepository profileRepo;
	
	@Test
	public void selectByMemberProfile() {
//		profileRepo.selectByMemberProfile("hhnn.1").forEach(arr->{
//			System.out.println(Arrays.toString(arr));
//		});
		List<Object[]> resultList = profileRepo.selectByMemberProfile("hhnn.1");
		for(Object[] arr : resultList) {
			MemberDTO member = (MemberDTO)arr[0];
			ProfileDTO profile = (ProfileDTO)arr[1];
			System.out.println(member.getMid() + "---" + member.getMname());
			System.out.println(profile.getPno() + "---" + profile.getPname());
		}
	}
	
	//@Test
	public void selectByMemberProfileCount2() {
		profileRepo.selectByMemberProfileCount2().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//@Test
	public void selectByMemberProfileCount() {
		profileRepo.selectByMemberProfileCount().forEach(arr->{
			System.out.println(Arrays.toString(arr));
		});
	}
	
	//@Test
	public void selectByPropertyProfile() {
		log.info("-----------pname으로 조회하기-------");
		profileRepo.findByPname("face4").forEach(profile->{
			log.info(profile.toString()); //info는 String profile은 객체
		});
		log.info("-----------CurrentYn으로 조회하기-------");
		profileRepo.findByCurrentYn(true).forEach(profile->{
			log.info(profile.toString()); //info는 String profile은 객체
		});
		log.info("-----------Member으로 조회하기-------");
//		MemberDTO member = new MemberDTO();
//		member.setMid("hhnn.1");
		MemberDTO member = MemberDTO.builder().mid("hhnn.1").build();
		profileRepo.findByMember(member).forEach(profile->{
			log.info(profile.toString()); //info는 String profile은 객체
		});
	}
	
	//@Test
	public void selectByIdProfile() {
		profileRepo.findById(6L).ifPresent(p->{
			System.out.println(p);
			System.out.println(p.getPname());
			System.out.println(p.getMember());
			System.out.println(p.getMember().getMname());
		});
	}
	
	//@Test
	public void selectAllProfile() {
		profileRepo.findAll().forEach(profile->{
			//System.out.println(profile);
			System.out.println(profile.getMember().getMname() + "의 " + (profile.isCurrentYn()?"현재":"과거") + " 프로파일" + profile.getPname());
		});
		//참조한 것 모두 다나옴(@ManyToOne private MemberDTO member)
		//ProfileDTO(pno=5, pname=face5, currentYn=true, member=MemberDTO(mid=hhnn.1, mpassword=1234, mname=일반회원1, mrole=USER))
	}
	
	//@Test
	public void selectAllMember() {
		memberRepo.findAll().forEach(member->{
			System.out.println(member);
		});
		//MemberDTO(mid=hhnn.1, mpassword=1234, mname=일반회원1, mrole=USER)
	}
	
	//@Test
	public void insertProfile() {
		// hhnn.1, hhnn.10 두 멤버의 프로파일을 5개씩 넣기
		MemberDTO m1 = memberRepo.findById("hhnn.1").get(); //있냐없냐(findbyid>optional) > 쓰려고(get)
		MemberDTO m10 = memberRepo.findById("hhnn.10").get();
		IntStream.rangeClosed(1, 5).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder().pname("face" + i).currentYn(i==5?true:false).member(m1).build();
			profileRepo.save(profile);
		});
		IntStream.rangeClosed(1, 5).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder().pname("picture" + i).currentYn(i==5?true:false).member(m10).build(); //member 참조
			profileRepo.save(profile);
		});
	}
	
	//@Test
	public void insertMember() {
		//10건의 member insert
		IntStream.rangeClosed(1, 10).forEach(i->{
			MemberDTO m1 = null;
			if(i>5) {
				m1 = MemberDTO.builder().mid("hhnn." + i).mname("매니져" + i).mpassword("1234").mrole(MemberRole.MANAGER).build();
			} else {
				m1 = MemberDTO.builder().mid("hhnn." + i).mname("일반회원" + i).mpassword("1234").mrole(MemberRole.USER).build();
			}
			memberRepo.save(m1);
		});
	}
	
}
