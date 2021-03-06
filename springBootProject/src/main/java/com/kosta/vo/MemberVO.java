package com.kosta.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name="member")
public class MemberVO {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long mid;
	private String mpassword;
	private String mname;
	private String phone;
	private String address;
	private String email;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
}

/*
Create table member(
mid number primary key,
mpassword varchar2(20),
mname varchar2(50),
phone varchar2(20),
address varchar2(100),
email  varchar2(20) ,
regdate timestamp,
updatedate timestamp
);
*/