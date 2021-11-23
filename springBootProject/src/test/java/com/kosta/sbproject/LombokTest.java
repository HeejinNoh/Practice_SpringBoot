package com.kosta.sbproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.vo.CarVO;

@SpringBootTest
public class LombokTest {

	@Test //junit으로 test
	public void test1() {
		CarVO car1 = new CarVO();
		CarVO car2 = new CarVO("BMW", 7000);
		CarVO car3 = CarVO.builder().model("모닝").price(1000).build();
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
	}
}
