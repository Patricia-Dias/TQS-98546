CREATE TABLE students (
  nmec BIGSERIAL PRIMARY KEY,
  name varchar(255) not null,
  birthdate date,
  classletter char
);

INSERT INTO students (name, birthdate, classletter) VALUES('dummy', '1815-03-02', 'A');