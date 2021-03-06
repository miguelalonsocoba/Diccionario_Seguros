package com.example.dicionario.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.Tag;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** The constant TAG_1. */
	public static final String TAG_1 = "Swagger Resource.";

	/**
	 * Controla los puntos finales expuestos por Swagger.
	 * 
	 * @return Object Docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error"))).build()
				.tags(new Tag(TAG_1, "Operaciones CRUD de Diccionario.")).apiInfo(metaData());
	}

	/**
	 * Meta data.
	 *
	 * @return the api info
	 */
	@SuppressWarnings("rawtypes")
	private static ApiInfo metaData() {
		Collection<VendorExtension> vendors = new ArrayList<>();
		vendors.add(new StringVendorExtension("MAC", "MAC"));
		return new ApiInfo("DiccionarioSeguros", "Aplicacion de un diccionario de Empresa de Seguros", "1.0",
				"URL de Terminos de servicio",
				new Contact("COM", "https://github.com/miguelalonsocoba/Diccionario_Seguros.git", ""),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", vendors);
	}

}
