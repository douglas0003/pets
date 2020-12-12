package com.douglasferreira.testepets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
        		.groupName("Cadastro API")
        		.apiInfo(this.apiInfo())
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.douglasferreira.testepets"))
                .paths(PathSelectors.any())
                .build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Cadastro API")
				.description("Serviços criados para o teste da Pets.")
				.version("1.0.0 - Douglas Porciuncula Ferreira")
				.build();
	}
	
}
