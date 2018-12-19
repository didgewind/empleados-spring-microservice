package profe.empleados.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Configuración de seguridad para el microservicio de empleados
 * @author made
 *
 */
@EnableWebSecurity
@Configuration
public class EmpleadosSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtFilter jwtFilter;
	
	/*
	 * Configuramos la seguridad de la siguiente manera:
	 * 
	 * - Autorizamos accesos
	 * 
	 * - Añadimos el filtro de gestión jwt
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/empleados", "/empleados/*").hasAnyRole("USER", "ADMIN")
				.antMatchers("/empleados", "/empleados/*").hasRole("ADMIN")
				.antMatchers("/").permitAll()
				.and()
            // Cualquier petición pasará por este filtro para validar el token
            .addFilterBefore(jwtFilter,
            		BasicAuthenticationFilter.class);
	}

}
