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

@Service
@Transactional
public class EmpNegocioImpl implements EmpNegocio {

	@Resource(name="daoJdbc")
	//@Autowired
	private EmpDAO dao;
	
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.empleadosTopic}")
    private String empleadosTopic;

	public Empleado getEmpleado(String cif) {
		return dao.getEmpleado(cif);
	}

	public List<Empleado> getAllEmpleados() {
		return dao.getAllEmpleados();
	}

	public boolean insertaEmpleado(Empleado emp) {
		boolean bResult = dao.insertaEmpleado(emp);
		if (bResult) {
			kafkaTemplate.send(empleadosTopic, "Insertado el empleado " + emp);
		}
		return bResult;
	}

	public boolean modificaEmpleado(Empleado emp) {
		return dao.modificaEmpleado(emp);
	}

	public boolean eliminaEmpleado(String cif) {
		return dao.eliminaEmpleado(cif);
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









