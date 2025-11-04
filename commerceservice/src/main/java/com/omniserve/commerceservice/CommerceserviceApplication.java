package com.omniserve.commerceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
		scanBasePackages = {
				"com.omniserve.commerceservice",
				"com.omniserve.commondblib"
		}
)
@EntityScan(basePackages = "com.omniserve.commondblib.entity")
@EnableJpaRepositories(basePackages = "com.omniserve.commondblib.repository")
public class CommerceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceserviceApplication.class, args);
	}

}
