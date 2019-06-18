package profe.empleados.mvc.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import profe.empleados.model.Empleado;
import profe.empleados.negocio.EmpNegocio;

/**
 * Códigos de respuesta en caso de error de petición:
 * 
 * 	FORBIDDEN en caso de no autorizado
 * 	CONFLICT en caso de registro duplicado (insert)
 * 	NOT_FOUND si el registro no existe (delete, update, get)
 * @author made
 *
 */
@RestController
@RequestMapping("/empleados")
public class EmpleadosRestController {

	private Logger logger = Logger.getLogger(EmpleadosRestController.class
			.getName());

	@Autowired
	private EmpNegocio negocio;
	
	@RequestMapping(value="/{cif}", method=RequestMethod.GET)
	public Empleado getEmpleado(@PathVariable String cif, 
			HttpServletResponse response) throws IOException {
		Empleado emp = negocio.getEmpleado(cif);
		if (null == emp) {
			response.sendError(HttpStatus.NOT_FOUND.value());
		}
		return emp;
	}
	
	@GetMapping
	public List<Empleado> getAllEmpleados() {
		logger.info("Petición getAllEmpleados() recibida");
		return negocio.getAllEmpleados();
	}
	
	
	@PostMapping
	public ResponseEntity<Void> nuevoEmpleado(@RequestBody Empleado empleado, 
			HttpServletResponse response) throws IOException {
		if(negocio.insertaEmpleado(empleado)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else { // Error, probablemente el empleado no exista
			logger.info("Intentando insertar un empleado que ya existe");
			response.sendError(HttpStatus.CONFLICT.value());
			return null;
		}
	}

	/*
	 * Usamos el cif del empleado recibido en la url, el del body
	 * lo ignoramos. Podrían combinarse para cambiar el cif en la bd
	 */
	@RequestMapping(value="/{cif}", method=RequestMethod.PUT)
	public ResponseEntity<String> modificaEmpleado(@PathVariable String cif, 
			@RequestBody Empleado empleado, 
			HttpServletResponse response) throws IOException {
		empleado.setCif(cif);
		if(negocio.modificaEmpleado(empleado)) {
			return new ResponseEntity<String>("Todo ok", HttpStatus.OK);
		} else { // Error, probablemente el empleado no exista
			response.sendError(HttpStatus.NOT_FOUND.value());
			return null;
		}
	}
	
	@RequestMapping(value="/{cif}", method=RequestMethod.DELETE)
	public ResponseEntity<String> eliminaEmpleado(@PathVariable String cif, 
			HttpServletResponse response) throws IOException {
		if (negocio.eliminaEmpleado(cif)) {
			return new ResponseEntity<String>("Todo ok", HttpStatus.OK);
		} else { // Error, probablemente el empleado no exista
			response.sendError(HttpStatus.NOT_FOUND.value());
			return null;
		}
	}	

}















