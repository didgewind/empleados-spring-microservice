package profe.empleados.daos;

import java.util.List;

import profe.empleados.model.Empleado;

public interface EmpDAO {

	Empleado getEmpleado(String cif);
	
	List<Empleado> getAllEmpleados();
	
	boolean insertaEmpleado(Empleado emp);

	boolean modificaEmpleado(Empleado emp);

	boolean eliminaEmpleado(String cif);

}
