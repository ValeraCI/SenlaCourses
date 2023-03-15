# Music Application

This project is a web application that simulates the work of a music service. The application is developed using Maven as a dependency management tool. The web application uses the following technologies:

Spring Framework
>Main technology used in an application to manage an IoC container and DI dependencies. In particular, Spring Core, Spring Context, Spring MVC and Spring ORM are used.

Hibernate
>ORM framework which is used to work with a database. The project uses Hibernate Core and Hibernate JPAModelgen.

PostgreSQL
>DBMS used to store data in an application.

Liquibase
>Tool for managing database migrations. In the project, it is used for version control of the database schema.

SLF4J and Log4j
>Libraries for logging an application.

Spring Security
>Framework for securing web applications based on Spring. The project uses Spring Security Config, Spring Security Web and Spring Security Test.

The Jakarta Servlet API
>Standard interface for developing Java web applications.

Jackson
>Library for working with JSON in Java. It is used in the project for serialization and deserialization of objects.

JJWT
>Library for working with JSON Web Tokens. It is used in the project to generate and verify authorization tokens.

JUnit Jupiter
>Framework for testing Java code. It is used in the project for writing unit tests.
---
## All urls that the application supports:

### /authenticate
```Get: /authenticate/login```
JSON is sent to this address in the format:
> {
"email": "",
"password": ""
}

The response returns a JJWT, which must be sent in the future for all requests

---
### /accounts
```Post: /accounts/register```
JSON is sent to this address in the format:
> {
"nickname": "",
"email": "",
"password" : ""
}

Required to add a user to the database

```Get: /accounts?pageNumber=1```
Serves to get all users. One page contains 10 entries. By default, pageNumber is equal to one. If a negative page is specified, the first one will be returned. If a non-existent page is specified, the last one will be returned

```Get: /accounts/{id}```
Returns an account with the id index

```Delete: /accounts/{id}```
Deletes an account with the id index if your id matches the one being deleted or if your role is administrator or higher

```Patch: /accounts/{id}```
JSON is sent to this address in the format:
>   {
"nickname" : "",
"password" : ""
}

Updates the account with the id index if your id matches the one being updated or if your role is administrator and higher

```Post: /accounts/{accountId}/albums/{albumId}```

Adds an album with an id equal to albumId to the saved user with an id equal to AccountId. Available only if your id is equal to the AccountId or if your role is administrator and higher

```Delete: /accounts/{accountId}/albums/{albumId}```

Deletes an album with an id equal to albumId from saved to a user with an id equal to AccountId. Available only if your id is equal to the AccountId or if your role is administrator and higher

```Patch: /accounts/role/{id}```
JSON is sent to this address in the format:
>   {
"roleId" : ""
}

Updates the role to an account with an id equal to the id for a role with an id RoleId. Available only to an account with the Owner role. In the case of transferring the Owner role, the sender receives the Administrator field

---
### /albums

```Get: /albums/{id}```
Returns an album with the id index

```Get: /albums/search/{title}?pageNumber=1```
Returns albums whose name starts with title. One page contains 10 entries. By default, the page number is one. If a negative page is specified, the first one will be returned. If a non-existent page is specified, the last one will be returned

```Post: /albums```
JSON is sent to this address in the format:
>   {
"title": "",
"creatorId": ""
}

Required to add an album to the database

```Patch: /albums/{id}```
JSON is sent to this address in the format:
>   {
"title" : ""
}

Updates the album with the id index if you are the creator of this album or if your role is administrator and above

```Get: /albums?pageNumber=1```
Serves to get all albums. One page contains 10 entries. By default, the page number is one. If a negative page is specified, the first one will be returned. If a non-existent page is specified, the last one will be returned

```Delete: /albums/{id}```
Deletes an album with the id index if the sending request is the creator of the album or if it has the Administrator role and higher

```Post: /albums/{albumId}/songs/{songId}```
Adds a song with an id equal to songId to an album with an id equal to albumId. Works if the sending request is the creator of the album or if it has the Administrator role or higher

```Delete: /albums/{albumId}/songs/{songId}```
Deletes a song with an id equal to songId from an album with an id equal to albumId. Works if the sending request is the creator of the album or if it has the Administrator role or higher

```Get: /albums/savedAlbums/{id}```
Returns albums that are saved by a user with an id equal to id

```Get: /albums/createdAlbums/{id}```
Returns albums created by a user with an id equal to id

```Get: /albums/recommendations?limit=10```
Returns a list of albums recommended for listening for the user who sent the request. The number of albums is equal to the limit. By default, limit is 10

---

### /songs
```Post: /songs```
JSON is sent to this address in the format:
>   {
"title": "",
"genreId": "",
"authorsId": [{""},..., {""},
"albumCreator": "",
"albumId": ""
}

Required to add a song to the database

```Delete: /songs/{id}```
It is used to delete a song with the id index. A song can be deleted by any of its authors or by a user with the Administrator role and above

```Get: /songs/{id}```
Returns a song with the id index

```Get: /songs/search/{parameter}?findBy=BY_TITLE```
Returns songs by title or by genre. findBy can accept BY_TITLE, BY_GENRE. By default, BY_TITLE is used

```Get: /songs/search/album/{albumId}```
Returns songs which are in the album whose index matches the albumId
