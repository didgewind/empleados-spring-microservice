package profe.empleados.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages= {"profe.empleados"}, 
	excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EmpleadosApp.class))
//@EnableDiscoveryClient
public class EmpleadosApp {
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "empleados-server");
		SpringApplication.run(EmpleadosApp.class, args);
	}
	
}
