package com.kosta.vo2;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid") //  특정한 애만 같으면 같은거! 아니면 전체다 비교해서 같으면 같다고 인식!
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;

	//JPA는 default로 지연로딩을 사용한다.(pdsboard조회시 pdsfile 조인하지 않음)
	//default: fetch = FetchType.LAZY..PDSFile 연결 불가.. @Query로 해결
	//즉시로딩 fetch = FetchType.EAGER...PDSFile 연결 가능
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pdsno") 
	//새로운 테이블이 생성되지않고 컬럼 하나가 임의로 생김. 1:n이기 때문에 n인 tbl_pdsfiles에 컬럼생성
	private List<PDSFile> files2;
}
