package com.example.memberservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceApplicationTests {

	@Test
	void contextLoads() {
		try {
			// Spring 컨텍스트 자동 로드
		} catch (Exception e) {
			e.printStackTrace(); // 원인 메시지 확인
			throw e; // 테스트 실패 유지
		}

	}

}
