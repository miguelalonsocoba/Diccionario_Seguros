package com.example.dicionario.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import springfox.documentation.spring.web.plugins.Docket;

class SwaggerConfigTest {

	private SwaggerConfig sc = new SwaggerConfig();

	@Test
	void test_product() {
		Docket p = sc.productApi();
		assertNotNull(p);
		assert (p instanceof Docket);
	}

}
