package profe.empleados.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class EmpleadosWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("profe").password("{noop}profe").roles("USER")
				.and()
			.withUser("admin").password("{noop}admin").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/empleados", "/empleados/*").hasAnyRole("USER", "ADMIN")
				.antMatchers("/empleados", "/empleados/*").hasRole("ADMIN")
				.antMatchers("/").permitAll()
				.and()
			.formLogin()
				.and()
			.httpBasic()  // Para poder hacer peticiones desde postman
				.and()
			.csrf().disable()
			.logout()
				.logoutSuccessUrl("/");			
	}

	/*
	 * Para evitar que nos de el error 'There is no PasswordEncoder mapped for the id "null"'
	 * al hacer login con spring security 5
	 */
/*	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}*/
	
}
