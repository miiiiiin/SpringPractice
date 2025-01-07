package com.site.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.site.sbb")
public class SbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbbApplication.class, args);
		// 로드된 빈 목록 출력
		ApplicationContext ctx = SpringApplication.run(SbbApplication.class, args);
		System.out.println("Loaded Beans:");
		for (String beanName : ctx.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}


}
