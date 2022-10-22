package com.HighSchoolLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@PropertySource("classpath:application.properties")
public class HighSchoolLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighSchoolLibraryApplication.class, args);
	}

}
