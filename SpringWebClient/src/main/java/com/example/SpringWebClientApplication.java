package com.example;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.ObjectUtils;

import com.example._10_config.SpringWebClient;
import com.example._20_dto.DptDto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringWebClientApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(SpringWebClientApplication.class, args);
		
		SpringWebClient webClient = context.getBean(SpringWebClient.class);
				
		System.out.println(">> deleteDpt " + webClient.deleteDpt((long)40).block());

		DptDto insertDpt = new DptDto((long) 40, "Channel", 400);
		System.out.println(">> insertDpt " + webClient.insertDpt(insertDpt).block());
	
		DptDto updateDpt = new DptDto((long) 30, "Compliance", 300);
		System.out.println(">> updateDpt " + webClient.updateDpt(updateDpt).block());
		
//		System.out.println(">> findById " + (ObjectUtils.isEmpty(webClient.findById((long)30).block())? "no item found":"item found"));
//		System.out.println(">> findById " + (webClient.findById((long)30).block()==null? "no item found":"item found"));
		System.out.println(">> findById " + webClient.findById((long)30).block());
		
	    // We need to block for the content here or the JVM might exit before the data is logged
	    System.out.println(">> List of Dpts = " + webClient.findAllDpts().blockLast());
	}

}
