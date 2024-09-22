package com.example.kiranastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy
public class KiranastoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(KiranastoreApplication.class, args);
	}

}
