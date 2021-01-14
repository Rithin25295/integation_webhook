package com.indrasol.supervue.supervuewebhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.indrasol.supervue.supervuewebhook.service.MyResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableJpaRepositories(basePackages="com.indrasol.supervue.supervuewebhook")
@EntityScan(basePackages={"com.indrasol.supervue.supervuewebhook"})
public class AppConfig implements WebMvcConfigurer {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/assets/**")
          .addResourceLocations("/assets/"); 
    }
	
	@Autowired
	MyResponseErrorHandler resErrorHandler;
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "classpath:tiles.xml" });
		return tiles;

	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return resolver;
	}
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(resErrorHandler);
		return restTemplate;
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objMapper;
	}

}
