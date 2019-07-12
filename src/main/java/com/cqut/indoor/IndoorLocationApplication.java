package com.cqut.indoor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@EnableTransactionManagement
@MapperScan("com.cqut.indoor.mapper")
@EnableSwagger2
@EnableCaching
public class IndoorLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndoorLocationApplication.class, args);
	}
}
