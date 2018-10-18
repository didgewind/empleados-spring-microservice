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
		mpEmpleados.put("123452435T", new Empleado("123452435T", "Esthela", "Ruíz", 54)); 
		mpEmpleados.put("223452435A", new Empleado("223452435A", "Manuel", "Alonso", 64));   
		mpEmpleados.put("323452435B", new Empleado("323452435B", "Mirkka", "Touko", 22));    
		mpEmpleados.put("523452435S", new Empleado("523452435S", "Ethan", "Hawk", 47));      
		mpEmpleados.put("623452435D", new Empleado("623452435D", "Jesús", "Gutiérrez", 81));
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
	public void insertaEmpleado(Empleado emp) {
		mpEmpleados.put(emp.getCif(), emp);
	}

	@Override
	public void modificaEmpleado(Empleado emp) {
		this.insertaEmpleado(emp);
	}

	@Override
	public void eliminaEmpleado(String cif) {
		mpEmpleados.remove(cif);
	}

}
