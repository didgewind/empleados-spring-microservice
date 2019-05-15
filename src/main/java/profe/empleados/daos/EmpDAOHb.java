package profe.empleados.daos;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import profe.empleados.model.Empleado;

@Repository("daoHb")
public class EmpDAOHb implements EmpDAO {

	@Autowired
	private SessionFactory sf;
	
	/* * Devuelve la sesión activa para ser utilizada dentro del DAO */
	private Session getSession() {
		return sf.getCurrentSession();
	}

	@Override
	public Empleado getEmpleado(String cif) {
		Empleado emp = (Empleado) getSession().get(Empleado.class, cif);
		return emp;
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		return getSession().createQuery("from Empleado").list();
	}

	@Override
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean insertaEmpleado(Empleado emp) {
		getSession().persist(emp);
		return true;
	}

	@Override
	public boolean modificaEmpleado(Empleado emp) {
		getSession().update(emp);
		return true;
	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		/* Mejor así por si hay cascade delete en alguna relación */
		Empleado empAux = this.getEmpleado(cif);
		getSession().delete(empAux);
		return true;
	}

}
