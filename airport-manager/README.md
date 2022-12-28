## Airport manager
## Description
This application serves as an example to how I interpret a   
minimalistic airport manager application would look like. 

This application has the ability to register pilots as well as passengers.    
It also has the ability to register airplanes that will use the airport. 

Lastly to combine to two it has the ability to register a flight.   
The flight has a list of passengers, a pilot (MVP*), an airplane and an   
arrival time and place and departure time and destination airport. 

*MVP    
For the MVP a flight will only have one pilot. In future plans it will have the ability to register    
a Captain, and a First Officer. 

## UML



## How to run it localy
### Docker
to initiate the docker container run following command (from the docker folder)    
``docker compose --file .\docker-compose.yml up`` 

### Liquibase

After you have initialised the docker container Liquibase should do the database initialisation. 
In order to activate Liquibase simply run the application. 
If changes need to be made, construct a sql, xml, ... file containing the changes and 
add them to the resources/db/migration folder. Afterwards add the file name to the changelogs.xml file. 

### Profile

After starting the docker container
start application with profile 'local' for development.
This will add test data to the database.  

## Technologies

- Liquibase
- SpringBoot
- Testcontainers (should still be configured)
- Swagger-ui (should still be added)
- Lombok
