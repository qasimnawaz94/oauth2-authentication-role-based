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

```
server:
  port: 8080
  servlet:
   contextPath: /oauth-services

spring:
  application:
    name: OAuth Authentication
    
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: ${DB_URL:jdbc:mysql://localhost:3306/oauth2-security}?useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=UTC
    username: ${DB_USER:root}
    password: ${DB_PASS:admin321}
    
  jpa:
    hibernate:
     ddl-auto: create
     use-new-id-generator-mappings: false
    properties:
     hibernate:
      show_sql: true
      enable_lazy_load_no_trans: true

  jackson:
    serialization:
      FAIL_ON_EMPTY_BEANS: false
      WRITE_DATES_AS_TIMESTAMPS: false
      INDENT_OUTPUT: false
    deserialization:
      FAIL_ON_UNKNOWN_PROPERTIES: false
    default-property-inclusion: non-null

config:
  oauth2:
    accessTokenUri: ${APP_URL:http://localhost:8080}/oauth-services/oauth/token
    clientID: securityService_2020
    clientSecret: securitySecret@2020
    resource:
      id: oauth2-resource
    resourceURI: ${APP_URL:http://localhost:8080}/oauth-services/oauth/authorize
    validity:
      tokenTimeout: 3600
      refresh-token: 600000
    userAuthorizationUri: ${APP_URL:http://localhost:8080}/oauth-services/oauth/authorize

logging:
  level:
    com: 
      security: DEBUG
      zaxxer: INFO
      
    org:
      springframework: INFO
      hibernate:
        type: INFO
    jdbc:
      resultsettable: INFO
      sqltiming: INFO
      sqlonly: fatal
      audit: fatal
      resultset: fatal
      connection: fatal

log4jdbc:
  dump:
    sql:
      addsemicolon: true
      maxlinelength: 0
      extrablanklines: false

security:
  oauth2:
    client:
      grantType: client_credentials

```


## Side Note
```
In this module Permissions/Privileges are attached with Roles. Roles can be dynamically added and they are checked globally inside 
\oauth2-authentication-role-based\src\main\java\com\security\services\config\oauth\GlobalPermissionEvaluator.java
```

## DB_SCHEMA
![DB_SCHEMA](/oauth2-authentication-role-based/src/main/resources/images/DB_SCHEMA.PNG "DB schema diagram")


