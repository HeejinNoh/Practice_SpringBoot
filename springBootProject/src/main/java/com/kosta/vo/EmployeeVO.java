package com.kosta.vo;

import java.sql.Date;

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
@Table(name="tbl_emp")
public class EmployeeVO {
	@Id
	private int employee_id;
	private String first_name;
	private String last_name;
	//유니크 자체는 null허용
	@Column(nullable = false, unique = true)
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private int salary;
	private int commission_pct;
	private int manager_id;
	private int department_id;
}
