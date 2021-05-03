package profe.empleados.sagas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import profe.empleados.model.Empleado;
import profe.empleados.model.EmpleadosEvent;
import profe.empleados.model.EmpleadosEventType;

@Service
public class EmpleadosEventsProducer {

	private static final Logger logger = LoggerFactory.getLogger(EmpleadosEventsProducer.class);
	
    @Autowired
    private KafkaTemplate<String, EmpleadosEvent> kafkaTemplate;

    @Value("${app.empleadosTopic}")
    private String empleadosTopic;

    public void sendCreateEmpleadoEvent(Empleado emp) {
    	this.sendEmpleadosEvent(emp.getCif(), new EmpleadosEvent(EmpleadosEventType.CREATE, emp, "RRHH"));
    }
    
    public void sendDeleteEmpleadoEvent(String cif) {
    	this.sendEmpleadosEvent(cif, new EmpleadosEvent(EmpleadosEventType.DELETE, new Empleado(cif, null, null, 0)));
    }
    
    public void sendEmpleadosEvent(String key, EmpleadosEvent event) {
    	logger.info("Enviando el mensaje: " + event + " con clave " + key);
    	kafkaTemplate.send(empleadosTopic, key, event);
    }
}
