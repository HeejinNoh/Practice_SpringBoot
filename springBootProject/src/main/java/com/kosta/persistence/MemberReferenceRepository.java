package com.kosta.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.vo2.MemberDTO;

public interface MemberReferenceRepository extends PagingAndSortingRepository<MemberDTO, String>{

}
