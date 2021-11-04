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
@Primary
@Lazy
public class EmpDAOMock implements EmpDAO {

	private Map<String, Empleado> mpEmpleados =
			new HashMap<>();
	
	@PostConstruct
	public void init() {
		mpEmpleados.put("23412312H", 
				new Empleado("23412312H", "Javier", "Pascual",
						23));
		mpEmpleados.put("12345243T", new Empleado("12345243T", "Esthela", "Ruíz", 54)); 
		mpEmpleados.put("22345243A", new Empleado("22345243A", "Manuel", "Alonso", 64));   
		mpEmpleados.put("32345243B", new Empleado("32345243B", "Mirkka", "Touko", 22));    
		mpEmpleados.put("52345243S", new Empleado("52345243S", "Ethan", "Hawk", 47));      
		mpEmpleados.put("62345243D", new Empleado("62345243D", "Jesús", "Gutiérrez", 81));
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
