package com.denis.costeminimo;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CosteminimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosteminimoApplication.class, args);
	}
	//Bean para cambiar la configuracion de swagger
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI()
				.info(new Info()
						.title("Next Generation Transport Service")
						.version("0.1")
						.description("Somos una compañía de transporte de paquetería interprovincial dentro del territorio Español.")
				);
	}

}
