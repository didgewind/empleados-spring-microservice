package profe.empleados.mvc.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends OncePerRequestFilter {

	@Value("${app.jwtSecretKey}")
	private String jwtSecretKey;
	
	/*
	 * Esto funciona así: este filtro salta para cualquier petición, y va
	 * a insertar siempre un Authentication en el contexto de seguridad, que será
	 * un UsernamePasswordAuthenticationToken que incluye el user y sus roles
	 * en caso de que el token jwt recibido sea correcto y null en caso contrario.
	 * Luego ya es responsabilidad de spring el autorizar o no el acceso al recurso
	 * solicitado, aquí sólo proporcionamos el usuario que intenta acceder y sus roles.
	 */
    @Override
    public void doFilterInternal(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {


        Authentication authentication = null;

		// Obtenemos el token que viene en el encabezado de la peticion
		String token = request.getHeader("Authorization");

		// si hay un token presente, entonces lo validamos
		if (token != null) {
			Claims claims = Jwts
							.parser()
							.setSigningKey(jwtSecretKey)
							.parseClaimsJws(token.replace("Bearer", ""))
							.getBody();
			String user = claims.getSubject();

			// La contraseña no se envía en el token jwt ni se pide para ninguna petición
			// en este servicio
			// por este motivo devolvemos un UsernamePasswordAuthenticationToken sin
			// password
			if (user != null) {
				List<String> authorities = (List<String>) claims.get("authorities");
				authentication = new UsernamePasswordAuthenticationToken(user, null, 
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			}
		}

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }
}