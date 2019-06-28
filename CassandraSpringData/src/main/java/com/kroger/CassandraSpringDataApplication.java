package com.kroger;

import static com.kroger.util.CommonConstants.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = { BASE_PACKAGE })
@EnableAspectJAutoProxy
@EnableWebMvc
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
