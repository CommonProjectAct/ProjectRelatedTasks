package com.kroger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class CassandraSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraSpringDataApplication.class, args);
	}
	
	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {

	    return new RepositoryRestConfigurerAdapter() {

	        @Override
	        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	            config.setRepositoryDetectionStrategy(
	                    RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
	        }
	    };
	}

}
