package br.com.itau.desafio.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafio.utils.Constants;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket apiV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.regex("/.*"))
				.build()
				.apiInfo(info());
	}

	
	@SuppressWarnings("rawtypes")
	private ApiInfo info() {

        ApiInfo apiInfo = new ApiInfo(
        		Constants.TITLE_API,
        		Constants.TITLE_VERSION,
        		Constants.VERSION,
        		Constants.TERMS_OF_SERVICE,
                new Contact(Constants.AUTHOR, Constants.ITAU, Constants.EMAIL_DEV),
                Constants.LICENSE, Constants.LICENSE_LINK, new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
	
	
	@SuppressWarnings("serial")
	private List<ResponseMessage> responseMessage() {
	    return new ArrayList<ResponseMessage>() {{
	        add(new ResponseMessageBuilder()
	            .code(200)
	            .message(Constants.MESSAGE_CODE200)
	            .build());
	        add(new ResponseMessageBuilder()
	            .code(201)
	            .message(Constants.MESSAGE_CODE201)
	            .build());
	        add(new ResponseMessageBuilder()
		            .code(204)
		            .message(Constants.MESSAGE_CODE204)
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(401)
		            .message(Constants.MESSAGE_CODE401)
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(403)
		            .message(Constants.MESSAGE_CODE403)
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(404)
		            .message(Constants.MESSAGE_CODE404)
		            .build());
	        add(new ResponseMessageBuilder()
		            .code(404)
		            .message(Constants.MESSAGE_CODE500)
		            .build());
	    }};
	}
}