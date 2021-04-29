package profe.empleados.negocio;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import profe.empleados.daos.EmpDAO;
import profe.empleados.model.Empleado;
import profe.empleados.model.EmpleadosEvent;
import profe.empleados.model.EmpleadosEventType;
import profe.empleados.sagas.EmpleadosEventsProducer;

@Service
@Transactional
public class EmpNegocioImpl implements EmpNegocio {

	@Resource(name="daoJdbc")
	//@Autowired
	private EmpDAO dao;
	
	@Autowired
	private EmpleadosEventsProducer eventsProducer;
	
	public Empleado getEmpleado(String cif) {
		return dao.getEmpleado(cif);
	}

	public List<Empleado> getAllEmpleados() {
		return dao.getAllEmpleados();
	}

	public boolean insertaEmpleado(Empleado emp) {
		boolean bResult = dao.insertaEmpleado(emp);
		if (bResult) {
		/*
		 * Enviamos el evento con el cif del empleado como clave. Así nos aseguramos que todos los eventos
		 * que se producen sobre el mismo empleado van a la misma partición, por lo que mantenemos el
		 * orden de los eventos.
		 * Podemos probar a enviar el mismo mensaje 3 veces, primero con la clave y después sin ella,
		 * y tener tres instancias de departamentos service arrancadas para comprobar que cuando se envían
		 * los mensajes sin clave tenemos un RoundRobin, y con clave le llegan siempre a la misma instancia
		 * de departamento
		 */
		for (int i=0; i<3; i++)
			eventsProducer.sendEmpleadosEvent(emp.getCif(), new EmpleadosEvent(EmpleadosEventType.CREATE, emp));
		}
		return bResult;
	}

	public boolean modificaEmpleado(Empleado emp) {
		return dao.modificaEmpleado(emp);
	}

	public boolean eliminaEmpleado(String cif) {
		boolean bResult = dao.eliminaEmpleado(cif);
		if (bResult) {
			eventsProducer.sendEmpleadosEvent(cif, 
					new EmpleadosEvent(EmpleadosEventType.DELETE, new Empleado(cif, null, null, 0)));
		}
		return bResult;
	}

	@Override
	@Transactional
	public void insertaVariosEmpleados(List<Empleado> empleados) {
		for (Empleado emp : empleados) {
			dao.insertaEmpleado(emp);
		}
	}

	@Override
	public void eliminaVariosEmpleados(List<Empleado> empleados) {
		for (Empleado emp : empleados) {
			dao.eliminaEmpleado(emp.getCif());
		}
	}
	
}









