package profe.empleados.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@ComponentScan(basePackages= {"profe.empleados"}, 
	excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EmpleadosApp.class))
public class EmpleadosApp {
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "empleados-server");
		SpringApplication.run(EmpleadosApp.class, args);
	}
	
}
