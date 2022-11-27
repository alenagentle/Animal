package com.diasoft.animals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AnimalsApplication.class)
@ActiveProfiles("test")
class AnimalsApplicationTests {

	@Test
	void contextLoads() {
	}

}
