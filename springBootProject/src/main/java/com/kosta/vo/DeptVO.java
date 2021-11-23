package com.kosta.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="tbl_dept")
public class DeptVO {

	@Id 
	private Integer departmentId;
	@Column(nullable = false, length = 30, unique = true)
	private String departmentName;
	@Column(nullable = true)
	private Integer managerId;
	@Column(nullable = true)
	private Integer locationId;
	
}
