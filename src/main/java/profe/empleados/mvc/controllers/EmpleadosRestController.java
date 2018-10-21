package profe.empleados.mvc.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import profe.empleados.model.Empleado;
import profe.empleados.negocio.EmpNegocio;

@RestController
@RequestMapping("/empleados")
public class EmpleadosRestController {

	private Logger logger = Logger.getLogger(EmpleadosRestController.class
			.getName());

	@Autowired
	private EmpNegocio negocio;
	
	@RequestMapping(value="/{cif}", method=RequestMethod.GET)
	public Empleado getEmpleado(@PathVariable String cif) {
		return negocio.getEmpleado(cif);
	}
	
	@GetMapping
	public List<Empleado> getAllEmpleados() {
		logger.info("Petición getAllEmpleados() recibida");
		return negocio.getAllEmpleados();
	}
	
	
	@PutMapping
	public ResponseEntity<Void> nuevoEmpleado(
			@RequestBody Empleado empleado) {
		negocio.insertaEmpleado(empleado);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<String> modificaEmpleado(
			@RequestBody Empleado empleado) {
		negocio.modificaEmpleado(empleado);
		return new ResponseEntity<String>("Todo ok", HttpStatus.OK);
	}
	
	@RequestMapping(value="/{cif}", method=RequestMethod.DELETE)
	public ResponseEntity<String> eliminaEmpleado(@PathVariable String cif) {
		negocio.eliminaEmpleado(cif);
		return new ResponseEntity<String>("Todo ok", HttpStatus.OK);
	}

}















