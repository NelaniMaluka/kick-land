package com.examplekicklaandwebsite.KickLaand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KickLaandApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickLaandApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer () {
			 public void addCorsMappings(@NonNull CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("*")
				.allowedOrigins("https://kick-land.web.app","kick-land.firebaseapp.com","http://localhost:3000/");
			}
		};
	} 
}
