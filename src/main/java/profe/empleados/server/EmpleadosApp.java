package profe.empleados.server;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ComponentScan(basePackages= {"profe.empleados"}, 
	excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = EmpleadosApp.class))
@EnableDiscoveryClient
public class EmpleadosApp {
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "empleados-server");
		SpringApplication.run(EmpleadosApp.class, args);
	}

}
