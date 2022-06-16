package com.blog.config;

import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import io.swagger.models.Contact;
import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).
			apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any()).build();
}

	private ApiInfo getInfo() {
		
		 Contact contact = new Contact( "Sergey Kargopolov",
	               "https://www.appsdeveloperblog.com", 
	               "developer@www.appsdeveloperblog.com" );
	       
	       List<VendorExtension> vendorExtensions = new ArrayList<>();
		
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application", 
				"Developed to learn backend devlopment ",
				"1.0", "Terms of Service",
				contact,
				"License",
				"API", vendorExtensions);
	}
}