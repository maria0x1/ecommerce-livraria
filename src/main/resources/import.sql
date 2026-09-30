-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO editora (nome, cnpj) VALUES ('Alta Books', '11111111000100');
INSERT INTO editora (nome, cnpj) VALUES ('Bookman', '22222222000100');

INSERT INTO livro (titulo, autor, id_editora) VALUES ('Clean Code', 'Robert C. Martin', 1);
INSERT INTO livro (titulo, autor, id_editora) VALUES ('O Programador Pragmático', 'Andrew Hunt', 2);