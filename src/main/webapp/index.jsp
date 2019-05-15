<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empleados Rest simple</title>
</head>
<body>
<p>
<spring:message code="saludo"/>
</p>
<p>
Empleados simple con hibernate y actuator
</p>
<p><pre>
- GET /empleados/: devuelve todos los empleados
- GET /empleados/x: devuelve el empleado con cif x
- PUT /empleados/: inserta un empleado (sólo admin)
- DELETE /empleados/x: elimina el empleado con cif x (sólo admin)
- POST /empleados/: modifica un empleado (sólo admin)</pre>

</body>
</html>