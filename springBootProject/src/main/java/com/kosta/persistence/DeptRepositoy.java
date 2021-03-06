package com.kosta.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kosta.vo.DeptVO;

public interface DeptRepositoy extends CrudRepository<DeptVO, Integer>{

	//JPA규격에 맞는 함수를 정의한다. 대문자 지키기!!
	Optional<DeptVO> findByDepartmentName(String name);
	
	//JPQL(JPA Query Language)
	@Query("select d from DeptVO d where d.departmentId > ?1 order by d.departmentName")
	public List<DeptVO> findByDept1(Integer deptid);
	
	@Query("select d from DeptVO d where d.departmentId > :deptid order by d.departmentName")
	public List<DeptVO> findByDept2(@Param("deptid") Integer di);
	
	@Query("select d from #{#entityName} d where d.departmentId > :deptid order by d.departmentName")
	public List<DeptVO> findByDept3(@Param("deptid") Integer di);
	
	// nativeQuery: tbl_dept 엔티티이름이 아니라 *를 쓸 수 없음. 그러니 value, nativeQuery = true로 변경
	@Query(value = "select * from tbl_dept where department_id > :deptid order by 1", nativeQuery = true)
	public List<DeptVO> findByDept4(@Param("deptid") Integer di);
	
	// join
	@Query(value = "select d.department_name, e.first_name, e.salary "
			+ "from tbl_emp e join tbl_dept d using(department_id) "
			+ "where department_id = :deptid order by 1", nativeQuery = true)
	public List<Object[]> findByDeptEmp(@Param("deptid") Integer di);
}
