package com.obs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "OBS TECHNICAL TEST", version = "1.0", description = "OBS TECHNICAL TEST"))
public class ObsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ObsApplication.class, args);
	}
}
