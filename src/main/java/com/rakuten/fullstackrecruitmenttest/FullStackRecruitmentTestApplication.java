package com.rakuten.fullstackrecruitmenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages= {"com.rakuten.fullstackrecruitmenttest"})
@EnableJpaRepositories(basePackages="com.rakuten.fullstackrecruitmenttest.repository")
public class FullStackRecruitmentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackRecruitmentTestApplication.class, args);
	}
}
