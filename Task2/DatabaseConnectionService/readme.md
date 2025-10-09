# DB Connection Microservice
    - This is having 3 APIs
        1. GET API to fetch the entity based on given id
        2. POST API to create the entity data into database
        3. GET API to fetch all the entities from DB and transform them into a nested json structure with their id and parentId relationship

# LogMethodParam
    - Created a custom annotation LogMethodParam which is applicable on methods only
    - Created a logging aspect around LogMethodParam annotation
    - If a method is annotated with LogMethodParam annotation than aspect will log the traceId, method name and it's arguments using logger.

# DDL-Auto
    - When we run application, it will automatically creates the table and id sequence generator as per Entity model.

# URLs
    - GET : http://localhost:8084/db-connection-service/rest/v1/name-to-color-mappings/1
    - GET : http://localhost:8084/db-connection-service/rest/v1/name-to-color-mappings
    - POST : http://localhost:8084/db-connection-service/rest/v1/name-to-color-mapping
        - RequestBody : 
```json
{
    "parentId":13,
    "name":"Assassin",
    "color":"lightblue"
}
```