INSERT INTO editora (id, nome, cnpj, telefone, email)
VALUES (1, 'Alta Books', '11222333000181', '(11) 3000-1000', 'contato@altabooks.example');
INSERT INTO editora (id, nome, cnpj, telefone, email)
VALUES (2, 'Bookman', '11444777000161', '(51) 3000-2000', 'contato@bookman.example');

INSERT INTO categoria (id, nome, descricao, ativo)
VALUES (1, 'Tecnologia', 'Livros sobre tecnologia e desenvolvimento.', TRUE);
INSERT INTO categoria (id, nome, descricao, ativo)
VALUES (2, 'Literatura', 'Ficção e literatura geral.', TRUE);

INSERT INTO perfil_acesso (id, nivel, permissoes_detalhadas)
VALUES (1, 'ROOT', 'Acesso administrativo para uso exclusivo em desenvolvimento.');
INSERT INTO usuario_admin (id, nome_completo, login, senha_hash, data_criacao, id_perfil_acesso)
VALUES (1, 'Administrador de Desenvolvimento', 'root',
        'pbkdf2-sha256$210000$1Y1IkOC06Gdvukai0zj05A$4JJFGcxXkwLjGinPgNvmNHuzew4xBmGTaSm5G6EYrAU',
        TIMESTAMP '2026-01-01 12:00:00', 1);

INSERT INTO livro (id, titulo, autor, isbn, preco, data_publicacao, id_editora)
VALUES (1, 'Clean Code', 'Robert C. Martin', '9780132350884', 199.90, DATE '2008-08-01', 1);
INSERT INTO livro (id, titulo, autor, isbn, preco, data_publicacao, id_editora)
VALUES (2, 'O Programador Pragmático', 'Andrew Hunt', '9788575224151', 149.90, DATE '1999-10-20', 2);

INSERT INTO livro_fisico (id, peso_kg, quantidade_estoque, tipo_capa)
VALUES (1, 0.8, 10, 'BROCHURA');
INSERT INTO livro_digital (id, formato_arquivo, tamanho_mb, licenca_drm)
VALUES (2, 'PDF', 12.5, 'SEM-DRM');

INSERT INTO livro_categoria (id_livro, id_categoria) VALUES (1, 1);
INSERT INTO livro_categoria (id_livro, id_categoria) VALUES (2, 1);
INSERT INTO livro_categoria (id_livro, id_categoria) VALUES (2, 2);

SELECT setval(pg_get_serial_sequence('editora', 'id'), (SELECT MAX(id) FROM editora), TRUE);
SELECT setval(pg_get_serial_sequence('categoria', 'id'), (SELECT MAX(id) FROM categoria), TRUE);
SELECT setval(pg_get_serial_sequence('perfil_acesso', 'id'), (SELECT MAX(id) FROM perfil_acesso), TRUE);
SELECT setval(pg_get_serial_sequence('usuario_admin', 'id'), (SELECT MAX(id) FROM usuario_admin), TRUE);
SELECT setval(pg_get_serial_sequence('livro', 'id'), (SELECT MAX(id) FROM livro), TRUE);
