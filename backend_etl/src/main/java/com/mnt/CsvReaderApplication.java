package com.mnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//import org.apache.commons.net.ftp.FTPClient;

@SpringBootApplication
@EnableScheduling
public class CsvReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReaderApplication.class, args);
		
		System.out.println("Hello");
	}
	
}