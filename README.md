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

![](images/DockerfileBackend.png)



/roulettes/{id}/openRoulette
/roulettes/{idRoulette}/{bet}/{value}
/roulettes
/roulettes
/roulettes/{idRoulette}/closeRoulette
