package com.CSH;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CshApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(CshApplication.class, args);
	}
  
}
