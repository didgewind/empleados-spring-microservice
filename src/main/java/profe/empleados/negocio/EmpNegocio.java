package profe.empleados.negocio;

import java.util.List;

import profe.empleados.model.Empleado;

public interface EmpNegocio {

	Empleado getEmpleado(String cif);
	
	List<Empleado> getAllEmpleados();
	
	void insertaEmpleado(Empleado emp);

	void modificaEmpleado(Empleado emp);

	void eliminaEmpleado(String cif);
	
	void insertaVariosEmpleados(List<Empleado> empleados);
	
	void eliminaVariosEmpleados(List<Empleado> empleados);

}
