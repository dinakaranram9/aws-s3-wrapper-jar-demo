package com.demo.awss3wrapperjar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dinakaran.awss3wrapper.annotations.EnableS3Bucket;

@SpringBootApplication
@EnableS3Bucket
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
