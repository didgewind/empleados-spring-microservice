package profe.empleados.sagas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import profe.empleados.daos.EmpDAO;
import profe.empleados.model.EmpleadosEvent;
import profe.empleados.model.SagaControlEvent;
import profe.empleados.model.SagaOperationResult;

@Service
@Transactional
public class SagaEventsListener {

	@Autowired
	private EmpDAO dao;
	
	@Autowired
	private EmpleadosEventsProducer sagaProducer;
	
	private static final Logger logger = LoggerFactory.getLogger(SagaEventsListener.class);

    @KafkaListener(topics = "${app.sagaControlTopic}")
    public void receive(@Payload SagaControlEvent event,
                        @Headers MessageHeaders headers) {
    	logger.info("Recibido el mensaje: " + event);
        switch (event.getSagaOperationResult()) {
        
        case ROLLBACK:
        	dao.insertaEmpleado(event.getEmpleado());
        	logger.info("Operación compensatoria: reinsertado el empleado " + event.getEmpleado());
        	break;
        	
        case COMMIT:
        	// Confirmar operación
        }
    }
}
