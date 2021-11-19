# PetStore Application

## Introduction

PetStore is a MicroProfile application.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Deploy Application with Docker-Compose

### Starting local Mysql, Grafana and Prometheus

1) Open project folder and navigate to `deploy` directory
2) Run `docker-compose up -d`
3) Open http://localhost:3000/ and use admin:admin credentials
4) Navigate into http://localhost:3000/dashboards
5) Open Quarkus Microprofile Metrics dashboard

### Following Containers will be deployed.

1) Application with Native Binary on http://localhost:8080
2) MySQL server on http://localhost:3306
3) Prometheus for scraping metrics on http://localhost:9090
4) Grafana Dashboards for Quarkus on http://localhost:3000


## Run test suite

You can test your services in dev mode by running test cases inside the _**src/test**_ directory.

## Curl commands

##### Commands for Pet Service

1) Add a new Pet

   `curl --location --request POST 'http://localhost:8080/v1/pets/addpet' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "petType":"Cat",
   "petName":"Bella",
   "petAge" : 2
   }'`

2) Get all pets

   `curl --location --request GET 'http://localhost:8080/v1/pets'`

3) Delete a Pet

   `curl --location --request DELETE 'http://localhost:8080/v1/pets/delete/3'`

4) Search Pet by ID

   `curl --location --request GET 'http://localhost:8080/v1/pets/3'`

5) Update Pet Details

    `curl --location --request PUT 'http://localhost:8080/v1/pets/update' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "id":1,
      "petType":"puselk",
      "petName":"Bhalla30",
      "petAge" : 3
      }'`

6) Search by pet type (petType=Dog)

   `curl --location --request GET 'http://localhost:8080/v1/pets/search/type/Dog'`

7) Search by pet name (petName=Kevo)

   `curl --location --request GET 'http://localhost:8080/v1/pets/search/name/Kevo'`

8) Search by pet age (petAge=2)

   `curl --location --request GET 'http://localhost:8080/v1/pets/search/age/2'`


##### Commands for PetType Service

1) Add a new Pet Type "Tiger"

   `curl --location --request POST 'http://localhost:8080/v1/pettypes/addpettype' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "petType":"Tiger"
   }'`

2) Get all pet types

   `curl --location --request GET 'http://localhost:8080/v1/pettypes'`

3) Delete a Pet Type with id=3

   `curl --location --request DELETE 'http://localhost:8080/v1/pettypes/delete/3'`

4) Search Pet Type by ID

   `curl --location --request GET 'http://localhost:8080/v1/pettypes/1'`

5) Update Pet Type Details

   `curl --location --request PUT 'http://localhost:8080/v1/pettypes/update' \
   --header 'Content-Type: application/json' \
   --data-raw '{
   "id": 3,
   "petType":"Lion"
   }'`

