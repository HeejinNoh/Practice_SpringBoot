package com.kosta.vo2;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(exclude = "board") //양방향일때 무한룹에 빠질 수 있음. (단방향 tostring해야함) tostring주의. data에서는 수정불가
@Entity @Table(name = "tbl_free_replies")
@EqualsAndHashCode(of="rno") //댓글번호가 같은걸 같은거로 여긴다.
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FreeBoardReply {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long rno;
	String reply;
	String replyer;
	@CreationTimestamp
	Timestamp regdate;
	@UpdateTimestamp
	Timestamp updatedate;
	
	@JsonIgnore
	@ManyToOne //댓글 여러개는 한개의 board와 연관관계
	FreeBoard board; //board_bno 컬럼 생성
}
