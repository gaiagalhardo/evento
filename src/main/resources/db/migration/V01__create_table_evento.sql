CREATE TABLE evento (
       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(255) NOT NULL,
       descricao TEXT,
       data_criacao DATETIME NOT NULL,
       data_inicio DATETIME NOT NULL,
       data_final DATETIME NOT NULL,
       localizacao VARCHAR(255) NOT NULL,
       status VARCHAR(100) NOT NULL,
       foto_capa TEXT,
       url TEXT,
       organizador VARCHAR(255) NOT NULL,
       contato_organizador VARCHAR(20),
       capacidade BIGINT NOT NULL
);