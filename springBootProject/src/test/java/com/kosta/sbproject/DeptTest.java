package com.kosta.sbproject;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.persistence.DeptRepositoy;

@SpringBootTest
public class DeptTest {

	@Autowired
	DeptRepositoy drepo;
	
	@Test
	public void nativeQueryJoin() {
		drepo.findByDeptEmp(60).forEach(arr->{
			System.out.println(Arrays.toString(arr)); //배열로 가져오기
			for(Object obj : arr) { //배열 안의 값 하나씩 가져오기
				System.out.println(obj);
			}
			System.out.println("--------------------------");
		});
	}
	
	//@Test
	public void jpqlTestFindByDept() {
		drepo.findByDept1(100).forEach(dept->{
			System.out.println(dept);
		});
		System.out.println("findByDept1---------");
		drepo.findByDept2(100).forEach(dept->{
			System.out.println(dept);
		});
		System.out.println("findByDept2---------");
		drepo.findByDept3(100).forEach(dept->{
			System.out.println(dept);
		});
		System.out.println("findByDept3---------");
		drepo.findByDept4(100).forEach(dept->{
			System.out.println(dept);
		});
		System.out.println("findByDept4---------");
	}
	
	//@Test
	public void findByDepartmentName() {
		drepo.findByDepartmentName("Marketing2").ifPresent(dept->{
			System.out.println(dept);
		});
	}
	
	//@Test
	public void selectById() {
		drepo.findById(20).ifPresent(dept->{
			System.out.println(dept);
		});
	}
	
	//@Test
	public void selectAll() {
		drepo.findAll().forEach(dept->{
			System.out.println(dept);
		});
	}
}
