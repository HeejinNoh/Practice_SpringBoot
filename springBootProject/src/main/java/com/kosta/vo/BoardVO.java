package com.kosta.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="tbl_boards")
public class BoardVO {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) //oracle: sequence, mysql:identity
	private Long bno;
	@NonNull //lombok
	@Column(nullable = false) //실제DB테이블 넣을때
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regdate;
	@UpdateTimestamp
	private Timestamp updatedate;
	
	/* 컬럼이름은 예약어 사용하지 않기
	 * ex) uid, update... 
	 * */
}
