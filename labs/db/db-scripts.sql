CREATE DATABASE empsvc;

USE empsvc;

CREATE TABLE empsvc.employee
(
	id serial primary key,
	name text,
	age integer,
	gender text,
	contractor boolean,
	designation text,
	department text,
	address text,
	country text
)

SELECT * FROM employee;