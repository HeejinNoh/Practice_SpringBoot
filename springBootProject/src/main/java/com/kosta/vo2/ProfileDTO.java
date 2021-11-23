package com.kosta.vo2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of={"pno"}) //pno가 같으면 같은것!
@Entity @Table(name="tbl_profile")
public class ProfileDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pno;
	private String pname;
	private boolean currentYn;
	
	@ManyToOne
	private MemberDTO member; //member_mid
}
// profileDTO가 memberDTO를 참조
// tbl_profile테이블에 tbl_member테이블의 키값 mid가 fk로 생성한다.