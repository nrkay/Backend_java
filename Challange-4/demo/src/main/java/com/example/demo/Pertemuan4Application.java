package com.example.demo;

import com.example.demo.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pertemuan4Application {

	public static void main(String[] args) {

		HomeController homeController = SpringApplication.run(Pertemuan4Application.class, args).getBean(HomeController.class);
		homeController.home();
	}

}
