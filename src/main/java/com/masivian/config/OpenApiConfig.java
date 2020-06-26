package com.masivian.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	public OpenAPI customConfiguration() {
		return new OpenAPI().components(new Components())
				.info(new Info().title("Roulette API Docs").description("Sample REST API documentation"));
	}

}
