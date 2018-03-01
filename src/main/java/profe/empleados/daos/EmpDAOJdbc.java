package profe.empleados.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import profe.empleados.model.Empleado;

@Repository(value="daoJdbc")
//@Primary
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
		return this.jdbcTemplate.queryForObject(
				ConstantesSQL.SELECT_EMPLEADO,
				new Object[]{cif}, new EmpleadoMapper());
	}

	@Override
	public List<Empleado> getAllEmpleados() {
		EmpleadoMapper miMapper = new EmpleadoMapper();
		return this.jdbcTemplate.query(ConstantesSQL.SELECT_ALL_EMPLEADOS, 
				miMapper);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertaEmpleado(Empleado emp) {
		this.jdbcTemplate.update(ConstantesSQL.INSERTA_EMPLEADO,
				emp.getCif(), emp.getNombre(), emp.getApellidos(), 
				emp.getEdad());
	}

	@Override
	public void modificaEmpleado(Empleado emp) {
		this.jdbcTemplate.update(ConstantesSQL.MODIFICA_EMPLEADO,
				emp.getNombre(), emp.getApellidos(), 
				emp.getEdad(), emp.getCif());
	}

	@Override
	public void eliminaEmpleado(String cif) {
		this.jdbcTemplate.update(ConstantesSQL.ELIMINA_EMPLEADO, cif);
	}

}
