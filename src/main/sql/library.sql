create database project1_db;

create table person
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(128) NOT NULL,
    birthyear INT CHECK ( birthyear >= EXTRACT(YEAR FROM now()) - 120 AND birthyear <= EXTRACT(YEAR FROM now()) )
);


create table book
(
    id           SERIAL PRIMARY KEY,
    person_id    INT,
    title        VARCHAR(128) NOT NULL ,
    author       VARCHAR(128) NOT NULL ,
    publish_year INT NOT NULL CHECK ( publish_year <= EXTRACT(YEAR FROM now())),
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE SET NULL
);


