package profe.empleados.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import profe.empleados.model.Empleado;

public class EmpDAODumb implements EmpDAO {

	private Empleado empleado = new Empleado("123434543Y",
			"Nunca", "Muero", 365);
	
	@Override
	public Empleado getEmpleado(String cif) {
		return this.empleado;
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		List<Empleado> lReturn = new ArrayList<>();
		lReturn.add(empleado);
		return lReturn;
	}

	@Override
	public boolean insertaEmpleado(Empleado emp) {
		return true;
	}

	@Override
	public boolean modificaEmpleado(Empleado emp) {
		return true;

	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		return true;
	}

}
