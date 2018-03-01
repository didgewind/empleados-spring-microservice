package profe.empleados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import profe.empleados.daos.EmpDAO;
import profe.empleados.daos.EmpDAODumb;

@Configuration
public class EmpleadosConfiguration {

	@Bean
	public EmpDAO daoDumb() {
		return new EmpDAODumb();
	}
}
