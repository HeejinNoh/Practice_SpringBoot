package com.kosta.vo2;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
@Entity @Table(name="tbl_members")
@EqualsAndHashCode(of={"mid"})
public class MemberDTO {

	@Id
	String mid; //key
	String mpassword;
	String mname;
	
	@Enumerated(EnumType.STRING)
	MemberRole mrole;
	
	
}
