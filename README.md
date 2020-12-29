# Spring Boot, OAuth 2.0 , Swagger-UI 2

## To Start ?

```
Execute the \oauth2-authentication-role-based\src\main\resources\script.sql into database before starting.
use below command to start
$ mvn spring-boot:run
```

## Swagger-UI
* After starting the application OPEN Swagger-UI at(http://localhost:8080/oauth-services/swagger-ui.html#/)


## User Data

```
   superadmin | Secure20
```

## APPLICATION CONFIGURATION
* Edit the configuration in the file [application.yml](/oauth2-authentication-role-based/src/main/resources/application.yml)


## Side Note
```
In this module Permissions/Privileges are attached with Roles. Roles can be dynamically added and they are checked globally inside 
\oauth2-authentication-role-based\src\main\java\com\security\services\config\oauth\GlobalPermissionEvaluator.java
```

## DB_SCHEMA
![DB_SCHEMA](https://raw.githubusercontent.com/qasimnawaz94/oauth2-authentication-role-based/master/src/main/resources/images/DB_SCHEMA.PNG)


