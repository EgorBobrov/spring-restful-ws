package com.bobrove.ws.mobileappws;

import com.bobrove.ws.mobileappws.ws.shared.SpringApplicationContextAccessor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContextAccessor springApplicationContextAccessor() {
		return new SpringApplicationContextAccessor();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
