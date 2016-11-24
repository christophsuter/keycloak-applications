# keycloak-applications

This repo consists of 2 Keycloak example Applications:
* Dropwizard 1.0.0
  - Keycloak Adapter for Dropwizard: de.ahus1.keycloak.dropwizard:keycloak-dropwizard
* Spring Boot 1.4.0 
  - Keycloak Adapter for tomcat: keycloak-tomcat8-adapter

Booth these Applications are secured with keycloak.
 
# Dropwizard Application
A RESTful Web Service implemented and based on the Framework [Dropwizard](http://www.dropwizard.io).

## Start Shell Skript

Um die Applikation unabhängig von der Konsole lauffähig zu haben, kann sie per Shell Skript als Daemon gestartet

    $ ./startup.sh conf-dev.yml

und auch wieder gestoppt

    $ ./shutdown.sh

werden. Da beim Startup die Prozess-ID (PID) in das lokale File `.pid` eingetragen wird, kann die Anwendung einfach per `shutdown.sh` wieder gestoppt werden.


## Build

$ mvn clean install -Pdistribution

## Run
To start the application: 

    $ ./startup.sh conf-dev.yml

Stop the application:

    $ ./shutdown.sh


# Spring Boot Application

## Build

## Run
