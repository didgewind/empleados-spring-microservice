package profe.empleados.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import profe.empleados.model.Empleado;

@Repository(value="daoMock")
//@Primary
@Lazy
public class EmpDAOMock implements EmpDAO {

	private Map<String, Empleado> mpEmpleados =
			new HashMap<>();
	
	@PostConstruct
	public void init() {
		mpEmpleados.put("1", new Empleado("1", "Javier", "Pascual", 23));
		mpEmpleados.put("2", new Empleado("2", "Esthela", "Ruíz", 54)); 
		mpEmpleados.put("3", new Empleado("3", "Manuel", "Alonso", 64));   
		mpEmpleados.put("4", new Empleado("4", "Mirkka", "Touko", 22));    
		mpEmpleados.put("5", new Empleado("5", "Ethan", "Hawk", 47));      
		mpEmpleados.put("6", new Empleado("6", "Jesús", "Gutiérrez", 81));
	}

	@Override
	public Empleado getEmpleado(String cif) {
		return mpEmpleados.get(cif);
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		return new ArrayList<>(mpEmpleados.values());
	}

	@Override
	public boolean insertaEmpleado(Empleado emp) {
		if (!mpEmpleados.containsKey(emp.getCif())) {
			mpEmpleados.put(emp.getCif(), emp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean modificaEmpleado(Empleado emp) {
		if (mpEmpleados.containsKey(emp.getCif())) {
			mpEmpleados.put(emp.getCif(), emp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		return null != mpEmpleados.remove(cif);
	}

}
