package com.kosta.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data //3개 모두 포함한 어노테이션 //@Getter//@Setter//@ToString
@Builder
public class CarVO {
	String model;
	int price;
}
