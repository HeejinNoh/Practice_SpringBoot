package com.kosta.vo3;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.kosta.vo2.FreeBoard;
import com.kosta.vo2.FreeBoardReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_webboards")
public class WebBoard {


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	@NonNull //lombok
	@Column(nullable = false) //실제DB테이블 넣을때
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regdate; //yyyy-MM-dd hh:mm:ss
	@UpdateTimestamp
	private Timestamp updatedate;
	
}
