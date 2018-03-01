package profe.empleados.daos;

import java.util.List;

import profe.empleados.model.Empleado;

public interface EmpDAO {

	Empleado getEmpleado(String cif);
	
	List<Empleado> getAllEmpleados();
	
	void insertaEmpleado(Empleado emp);

	void modificaEmpleado(Empleado emp);

	void eliminaEmpleado(String cif);

}
