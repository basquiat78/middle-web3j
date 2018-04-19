package com.basquiat.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * SwaggerConfig
 * 
 * create by basquiat 2018.04.19
 * 
 * @See http://localhost:8090/basquiat/swagger-ui.html
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${com.basquiat.host}")
	String APP_HOST;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.basquiat.app.web3j.web"))
          .paths(PathSelectors.any())
//          .paths(PathSelectors.ant("/*"))
          .build()
          .apiInfo(apiInfo())
          .enableUrlTemplating(false)
          ;
    }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("EVA Rest API")
				.version("1.0.0")
				.licenseUrl("http://www." + APP_HOST + "/license")
				.description("Api Documentation")
				.build();
	}

}
