package com.kosta.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kosta.vo.MemberVO;

@Repository
public interface MemberRepository extends CrudRepository<MemberVO, Long>{

}
