Money Transfer REST API [![Build Status](https://travis-ci.com/Shpota/money-transfer-rest-api.svg?branch=master)](https://travis-ci.com/Shpota/money-transfer-rest-api) [![codecov](https://codecov.io/gh/Shpota/money-transfer-rest-api/branch/master/graph/badge.svg)](https://codecov.io/gh/Shpota/money-transfer-rest-api)
=======================

A simple REST API for money transfer between accounts implemented with
Thorntail, JAX-RS, JPA and H2 database.

### System requirements
Tha application requires `JDK 11` or higher.
### How to run
You can build and run the project using the following command
```sh
./mvnw --quiet package && java -jar target/bank-thorntail.jar
```
The API will be accessible on port 8080

### How to query the API
You can use the following `curl` request
```sh
curl -d '{"fromId":"1", "toId":"2", "amount":11.11}' \
    -H "Content-Type: application/json" \
    -X POST http://localhost:8080/transfer
```
By default the system preloads 4 accounts with IDs from 1 to 4.