package profe.empleados.sagas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import profe.empleados.model.EmpleadosEvent;
import profe.empleados.model.EmpleadosEventType;
import profe.empleados.model.SagaControlEvent;

@Service
public class EmpleadosEventsProducer {

    @Autowired
    private KafkaTemplate<String, EmpleadosEvent> kafkaTemplate;

    @Value("${app.empleadosTopic}")
    private String empleadosTopic;

    public void sendEmpleadosEvent(String key, EmpleadosEvent event) {
    	kafkaTemplate.send(empleadosTopic, key, event);
    }
}
