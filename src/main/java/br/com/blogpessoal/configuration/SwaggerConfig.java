package br.com.blogpessoal.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() { //o docket é o responsável por g
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.blogpessoal.controllers")).paths(PathSelectors.any()) //passa onde está localizado o controlador
				.build().apiInfo(metadata()).useDefaultResponseMessages(false);
				
	}

	public static ApiInfo metadata() { 
		return new ApiInfoBuilder()
				.title("API - Blog Pessoal")
				.description("Projeto API Spring - Blog Pessoal")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/abnerwerley")
				.contact(contact()).build();
	}

	private static Contact contact() {
		return new Contact("Abner Werley Silva", "https://github.com/abnerwerley", "abnerwerley77@gmail.com");
	}

	private static List<Response> responseMessage() {

		return new ArrayList<Response>() {

			private static final long serialVersionUID = 1L;
			{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("201").description("Criado!").build());
				add(new ResponseBuilder().code("400").description("Erro na requisição!").build());
				add(new ResponseBuilder().code("401").description("Não Autorizado!").build());
				add(new ResponseBuilder().code("403").description("Proibido").build());
				add(new ResponseBuilder().code("404").description("Não encontrado!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());

			}
		};

	}
}