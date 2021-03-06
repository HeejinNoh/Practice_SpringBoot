package com.kosta.vo2;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_freeboards")
@EqualsAndHashCode(of = "bno")
public class FreeBoard {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
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
	
	//양방향
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL) //, fetch = FetchType.EAGER
	List<FreeBoardReply> replies;
}
