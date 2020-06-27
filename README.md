# CleanCodeTest Masivian

Autor: Daniel Quintero

## Introducción

Para cumplir con la prueba técnica construí una API Web RESTful con Spring Boot y MongoDB.

## Prerrequisitos

Los servicios REST están desarrollados en Spring Boot y se conecta a base de datos MongoDB.
  - Se debe tener instalado Mongo para iniciar servidor de base de datos.
  - Se debe tener inslatado Maven para compilar e iniciar Spring Boot Application.

## Instrucciones

1. Clonar el repositorio:

        git clone https://github.com/danielq97/CleanCodeTest.git

      
2. Desde una terminal iniciar el servidor de Mongo, en Windows:

        mongod
      
   En Linux:
   
        sudo systemctl start mongodb
        
   Una vez iniciado el servidor de Mongo nos debera aparecer algo como esto en la terminal:

![](images/img1.JPG)

   Ahora debemos iniciar la Spring Boot Application. Para ello necesitamos consturir el jar de la aplicación, mediante Maven. Una vez construido el jar lo podemos ejecutar.

4. Construir el jar de la aplicación:

        mvn package

5. Ejecutar el jar de la aplicación:

        java -jar target/springboot-mongodb-0.0.1-SNAPSHOT.jar
        
   Ahora ya estamos preparados para probar los Endpoints.        
## Endpoints

Construí 5 Endpoints, están en el orden que se enunciaron en la prueba:

- /roulettes                                 POST
- /roulettes/{id}/openRoulette               PUT
- /roulettes/{idRoulette}/{bet}/{value}      PUT
- /roulettes/{idRoulette}/closeRoulette       PUT
- /roulettes                                 GET

### Primer Endpoint
