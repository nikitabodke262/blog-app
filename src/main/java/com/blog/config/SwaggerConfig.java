package com.blog.config;

import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;
import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).
			apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any()).build();
}

	private ApiInfo getInfo() {
		
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application", 
				"Developed to learn backend devlopment ",
				"1.0", "Terms of Service",
				new Contact(),
				"License",
				"API", Collections.emptyList() );
	}
}