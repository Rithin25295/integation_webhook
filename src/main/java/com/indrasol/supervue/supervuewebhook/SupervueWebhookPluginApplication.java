package com.indrasol.supervue.supervuewebhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
public class SupervueWebhookPluginApplication {


	public static void main(String[] args) {
		SpringApplication.run(SupervueWebhookPluginApplication.class, args);
	}


	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}
}
