INSERT INTO editora (nome, cnpj) VALUES ('Alta Books', '11111111000100');
INSERT INTO editora (nome, cnpj) VALUES ('Bookman', '22222222000100');

INSERT INTO livro (id, titulo, autor, id_editora) VALUES (1, 'Clean Code', 'Robert C. Martin', 1);
INSERT INTO livro (id, titulo, autor, id_editora) VALUES (2, 'O Programador Pragmático', 'Andrew Hunt', 2);

INSERT INTO livro_fisico (id, peso, estoque_disponivel) VALUES (1, 0.8, 10);
INSERT INTO livro_digital (id, formato, tamanho_arquivo) VALUES (2, 'PDF', 12.5);

SELECT setval(pg_get_serial_sequence('livro', 'id'), (SELECT MAX(id) FROM livro), true);