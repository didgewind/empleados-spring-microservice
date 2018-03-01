package profe.empleados.negocio;

import java.util.List;

import javax.annotation.Resource;

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

	public Empleado getEmpleado(String cif) {
		return dao.getEmpleado(cif);
	}

	public List<Empleado> getAllEmpleados() {
		return dao.getAllEmpleados();
	}

	public void insertaEmpleado(Empleado emp) {
		dao.insertaEmpleado(emp);
	}

	public void modificaEmpleado(Empleado emp) {
		dao.modificaEmpleado(emp);
	}

	public void eliminaEmpleado(String cif) {
		dao.eliminaEmpleado(cif);
	}

	public void setDao(EmpDAO dao) {
		this.dao = dao;
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









