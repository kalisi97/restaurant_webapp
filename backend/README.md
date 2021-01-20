# Restaurantapp-backend - Clojure REST API using Leiningen and MySQL

This Clojure project uses Compojure, Swagger and Ring. Swagger is used in order to make a "sweet API". This project also uses SQL Korma for communicating with the MySQL database. The project was developed as part of the assignment for the course Software Engineering Tools and Methodology on Master's studies - Software Engineering and Computer Sciences at the Faculty of Organization Sciences, University of Belgrade, Serbia.

[Leiningen](https://github.com/technomancy/leiningen) - a build automation and dependency management tool for Clojure.

[Ring](https://github.com/ring-clojure/ring) - a Clojure web applications library. Allows web applications to be constructed of modular components that can be shared among a variety of applications, web servers, and web frameworks.

[Compojure](https://github.com/weavejester/compojure) - a small routing library for Ring.

[Korma](https://github.com/korma/Korma) - one of the most popular SQL tool for Clojure.

## Requirements

To start the server you need to have Java installed as well as Clojure build tool Leiningen. To use the API with the data you need to have MySQL installed as well.

Check your Java version, you need at least version 8.

```
java -v
```
Install Clojure by installing the [Leiningen Build Tool](https://leiningen.org/#install)
Install [MySQL](https://dev.mysql.com/downloads/mysql/)

## Setting up the database

1. Login to the MySQL server and create database by running the query that contains data from the database.sql file in the [resources folder](https://github.com/kalisi97/restaurant_webapp/tree/main/backend/src/restaurantapp/resources) .

2. You can change database configuration [here](https://github.com/kalisi97/restaurant_webapp/blob/main/backend/src/restaurantapp/database.clj) .
You need to provide your username and password.

```{:classname "com.mysql.cj.jdbc.Driver" :subprotocol "mysql" :user "root" :password "Ipsilon97!" :subname "//localhost:3306/restaurantdb"} ```
                                   
## Starting the server

First, go ``` cd```
into the project root folder. 

### Then install dependencies

```
lein deps
```

### Start the server, run the Swagger "sweet API" for Clojure

```
lein ring server
```
This will open the browser window.

## Run tests

Clojure includes a unit-testing framework in its clojure.test namespace. It provides ways to name and group tests, make assertions, report results, and orchestrate test suites.

```
lein test
```

## References

This project was made using information from multiple tutorials and documentations, including:

1. http://practicalli.github.io/clojure-webapps/
2. https://www.anthony-galea.com/blog/post/getting-started-with-compojure-api/
3. http://korma.github.io/Korma/
4. http://metosin.github.io/ring-swagger/doc/index.html
5. https://nakkaya.com/2009/12/14/using-mysql-with-clojure/
6. https://www.metosin.fi/blog/schema-spec-web-devs/
7. https://fuqua.io/blog/2013/12/rest-apis-and-relational-databases-in-clojure/
8. https://kendru.github.io/restful-clojure/2014/03/01/building-out-the-web-service-restful-clojure-part-3/
9. http://clojure.github.io/clojure/clojure.test-api.html
10. https://sodocumentation.net/clojure/topic/1901/clojure-test
