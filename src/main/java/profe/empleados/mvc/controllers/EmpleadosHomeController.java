package profe.empleados.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmpleadosHomeController {

	
	@GetMapping()
	public String home() {
		return "index.jsp";
	}

}















