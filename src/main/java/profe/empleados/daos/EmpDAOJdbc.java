package profe.empleados.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import profe.empleados.model.Empleado;

@Repository(value="daoJdbc")
@Primary
@Lazy
public class EmpDAOJdbc implements EmpDAO {

	private JdbcTemplate jdbcTemplate;
	
	private interface ConstantesSQL {
		String SELECT_EMPLEADO = 
				"select * from empleados where cif=?";
		String SELECT_ALL_EMPLEADOS =
				"select * from empleados";
		String INSERTA_EMPLEADO =
				"insert into empleados values(?, ?, ?, ?)";
		String MODIFICA_EMPLEADO =
				"update empleados set nombre=?, apellidos=?, edad=? where cif =?";
		String ELIMINA_EMPLEADO =
				"delete from empleados where cif=?";
	}
	
	private Logger logger = Logger.getLogger(EmpDAOJdbc.class
			.getName());

	private static final class EmpleadoMapper 
			implements RowMapper<Empleado> {

		public Empleado mapRow(ResultSet rs, int numRow) 
				throws SQLException {
			Empleado emp = new Empleado();
			emp.setCif(rs.getString("cif"));
			emp.setNombre(rs.getString("nombre"));
			emp.setApellidos(rs.getString("apellidos"));
			emp.setEdad(rs.getInt("edad"));
			return emp;
		}
	}

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public Empleado getEmpleado(String cif) {
		Empleado emp = null;
		try {
			emp = this.jdbcTemplate.queryForObject(
				ConstantesSQL.SELECT_EMPLEADO,
				new Object[]{cif}, new EmpleadoMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.info("Intento de recuperación de un empleado que no existe: " + cif);
		}
		return emp;
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		EmpleadoMapper miMapper = new EmpleadoMapper();
		return this.jdbcTemplate.query(ConstantesSQL.SELECT_ALL_EMPLEADOS, 
				miMapper);
	}

	@Override
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public boolean insertaEmpleado(Empleado emp) {
		try {
			this.jdbcTemplate.update(ConstantesSQL.INSERTA_EMPLEADO,
					emp.getCif(), emp.getNombre(), emp.getApellidos(), 
					emp.getEdad());
			return true;
		} catch (DataAccessException e) {
			logger.log(Level.SEVERE, "Error en insertaEmpleado del DAOJdbc", e);
			return false;
		}
	}

	@Override
	public boolean modificaEmpleado(Empleado emp) {
		return 1 == this.jdbcTemplate.update(ConstantesSQL.MODIFICA_EMPLEADO,
				emp.getNombre(), emp.getApellidos(), 
				emp.getEdad(), emp.getCif());
	}

	@Override
	public boolean eliminaEmpleado(String cif) {
		return 1 == this.jdbcTemplate.update(ConstantesSQL.ELIMINA_EMPLEADO, cif);
	}

}
