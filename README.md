# 2017 AMT Bootcamp Project #
*Authors: Sathiya Kirushnapillai & Mathieu Monteverde*

## The Pokemon Pokedex project ##
This repository contains the Pokemon Pokedex project for the AMT 2017 Bootcamp project. 
This JAVA EE application allows to list, add, edit and delete Pokemons. It is also possible to generate a large amount of random Pokemons. 

## Repository structure ##
- The **amt_bootcamp_java** folder contains the java application source code. This project uses maven.
- The **images** folder contains the Docker images that can be used to run the Pokemon Pokedex project.
- The **topology-amt** folder contains the docker-compose.yml file that describes the services (that are built from the docker images mentioned above) needed to run the application using Docker. The services are :
	- **mysql:** a Docker container to run MySQL.
	- **glassfish:** a Docker container to host and manage the Pokemon Pokedex Java EE application
	- **phpmyadmin:** an optional Docker container to inspect the MySQL container database

## Run the project ##
To run the Pokemon Pokedex project, clone or download this repository, open a terminal and move to the **topology-amt** folder. Then run the following command: 

```bash
docker-compose up --build
```

## See the result in a browser ##
The glassfish container runs on the port **8080**. The path to the application on the glassfish server is **amt_bootcamp_java-1.0-SNAPSHOT/**. Once it's running, you can see the project in your browser at the following URL:
```
http://localhost:8080/amt_bootcamp_java-1.0-SNAPSHOT/
```
*Note: It may take some time for the Docker containers to be up and running.*

## Build the Java application for Docker ##
Since the Java project uses Maven, you can build the *.war* file using the `mvn clean install` command in a Terminal from the **amt_bootcamp_java** folder. If you want to build the application to use it directly in Docker with your next `docker-compose` command, please use:
```bash
mvn clean install -PtoDocker
```
It will build the *.war* file inside the docker image folder, making it available for the next time you run the project.

*Note: a .war java file is already provided. You should not need to use this command if you didn't change the Java source code.*