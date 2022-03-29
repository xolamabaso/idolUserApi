# Sring Boot REST API with Maven
 Spring Boot CRUD RESTful API with H2 Database for data persistence.  

## H2 Database UI
To connect and view database
`
http://localhost:8080/h2-ui
`
View application.properties to verify details with UI

## SwaggerUI
To view the swagger API UI  
`
http://localhost:8080/swagger-ui.html
`

## URLs
Some basic requests are:
### GET
``
GET  Users /api/v1/users
GET  User  /api/v1/users/1 
``
### POST
`
POST  User  /api/v1/users
`
### PUT
`
PUT  User  /api/v1/users/1
`
### Delete
``
DELETE  User  /api/v1/users/1
DELETE Users  /api/v1/users
``

### CRUD Operations
When creating a new User they will be stored in the in memory database
