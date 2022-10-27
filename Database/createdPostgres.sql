-- create database HighSchoolLibrary;

DROP TABLE IF EXISTS  book, author,book_author;
DROP TABLE IF EXISTS  teacher,student, users;
DROP TABLE IF EXISTS  book;
DROP TYPE IF EXISTS category, userRole;

CREATE TYPE category AS ENUM ('Науково-Популярна');

CREATE TYPE userRole AS ENUM ('USER', 'OPERATOR','ADMIN');

CREATE TABLE author
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    createdAt timestamp default now()
);

CREATE TABLE book
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC NOT NULL CHECK(price>0),
    category category NOT NULL,
    count NUMERIC NOT NULL CHECK(count>=0),
    createdAt timestamp default now(),
    ts_description tsvector GENERATED ALWAYS AS (to_tsvector('english', description)) STORED
);

CREATE TABLE book_author
(
    bookId INTEGER REFERENCES book(id) NOT NULL,
    authorId INTEGER REFERENCES author(id) NOT NULL
);

CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    surname VARCHAR(128) NOT NULL,
    role userRole NOT NULL,
    login VARCHAR(128) UNIQUE NOT NULL,
    password VARCHAR(32) UNIQUE NOT NULL,
    type VARCHAR(32) NOT NULL,
    createdAt timestamp default now()
);

CREATE TABLE student
(
    id INTEGER REFERENCES users(id) NOT NULL,
    faculty VARCHAR(128) NOT NULL,
    "group" VARCHAR(32) NOT NULL,
    subgroup VARCHAR(32) NOT NULL
);

CREATE TABLE teacher
(
    id INTEGER REFERENCES users(id) NOT NULL,
    cathedra VARCHAR(128) NOT NULL,
    degree VARCHAR(32) NOT NULL,
    rank VARCHAR(128) NOT NULL
);
CREATE TABLE librarian
(
    id INTEGER REFERENCES users(id) NOT NULL,
    position VARCHAR(128) NOT NULL

);
CREATE TABLE administrator
(
    id INTEGER REFERENCES users(id) NOT NULL,
    degree VARCHAR(32) NOT NULL
);

