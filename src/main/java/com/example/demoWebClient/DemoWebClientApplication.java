package com.example.demoWebClient;

import com.example.demoWebClient.config.service.Impl.ConfigLoadServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.example.demoWebClient.**.dao")
public class DemoWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebClientApplication.class, args);
	}
}
