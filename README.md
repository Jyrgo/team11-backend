# General info
This project was written to gain understanding about writing a java backend service using spring security and jwt token session handling. The api is written for a simple system in which you can make an account, login, add games to the database and later edit them.

## How to run the project:
1. Download the project.
2. Assuming you are using intellij as your IDE then choose "import porject" from the welcome screen. 
3. On the import project screen tick the "Import project from existing sources" and then choose gradle.
4. Because we are using Lombok you need to make sure that annotation processing is enabled in the settings. For that navigate to "Settings -> Build -> Compiler -> Annotation Processors" and tick the enable option. 
5. Run the gradle build scripts.
6. Run the project.

## Technologies used in the project 
* Java Spring boot 
* H2 database
* Spring security 
* JWT
* Mockito 
* Lombok
* Gradle
* Spring Data JPA

## Deploying this app to the server
If you wish to deploy this application to your server I have written a [detailed guide](https://github.com/Jyrgo/team11-backend/blob/master/ServerSetup.md) on the subject.
