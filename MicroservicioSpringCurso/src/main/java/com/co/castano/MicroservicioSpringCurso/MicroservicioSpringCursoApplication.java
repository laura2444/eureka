package com.co.castano.MicroservicioSpringCurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.co.castano.MicroservicioCommonsService","com.co.castano"})
@EntityScan({"com.co.castano.MicroservicioSpringCurso.entity", "com.co.castano.MicroservicioCommonsService"})
@EnableJpaRepositories(basePackages = "com.co.castano.MicroservicioSpringCurso.repository")
public class MicroservicioSpringCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioSpringCursoApplication.class, args);
	}

}
