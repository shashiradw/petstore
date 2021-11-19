-- create the databases
CREATE DATABASE IF NOT EXISTS petstore;
USE petstore;
CREATE TABLE Pet
(
    id int NOT NULL AUTO_INCREMENT,
    petType varchar(255),
    petName varchar(255),
    petAge int,
    PRIMARY KEY (id)
);

CREATE TABLE PetType
(
    id int NOT NULL AUTO_INCREMENT,
    petType varchar(255),
    PRIMARY KEY (id)
);


INSERT INTO PetType VALUES
    (1,"Dog"),
    (2,"Cat"),
    (3,"Bird");

INSERT INTO Pet VALUES
    (1,"Dog","Tommy",2),
    (2,"Cat","Kity", 3),
    (3,"Bird","Kevo",1);

