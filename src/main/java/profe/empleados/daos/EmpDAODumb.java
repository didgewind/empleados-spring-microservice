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
	public void insertaEmpleado(Empleado emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificaEmpleado(Empleado emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminaEmpleado(String cif) {
		// TODO Auto-generated method stub

	}

}
