###Ruta del swagger
-ui: http://localhost:8080/swagger-ui.html.
-api-docs: http://localhost:8080/v2/api-docs.

###Ruta de Actuator:
-http://localhost:8080/management

###Documentación Jacoco:
https://reflectoring.io/jacoco/

###Configuración de Servicdor Tomcat
  Se necesita configruar el archivo "web.xml" que se encuentra en la ruta de "C:\apache-tomcat-8.5.61\webapps\manager\WEB-INF", ya el el war desplegado pesa mas de 50MB.
  lineas a modificar son: 
    <max-file-size>92428800</max-file-size>
    <max-request-size>92428800</max-request-size>
