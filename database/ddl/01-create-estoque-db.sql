=====================================================
-- CRIAÇÃO DO BANCO
-- =====================================================

CREATE DATABASE estoque_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE estoque_db;


-- =====================================================
-- PERMISSÃO PARA O USUÁRIO DA APLICAÇÃO
-- Execute conectado como root
-- =====================================================

GRANT ALL PRIVILEGES
ON estoque_db.*
TO 'c159119'@'%';

FLUSH PRIVILEGES;


-- =====================================================
-- TABELA PRODUTO
-- =====================================================

CREATE TABLE produto (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         sku VARCHAR(50) NOT NULL,
                         nome VARCHAR(150) NOT NULL,
                         ativo BOOLEAN NOT NULL DEFAULT TRUE,

                         CONSTRAINT pk_produto
                             PRIMARY KEY (id),

                         CONSTRAINT uk_produto_sku
                             UNIQUE (sku)
);


-- =====================================================
-- TABELA ESTOQUE
-- =====================================================

CREATE TABLE estoque (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         produto_id BIGINT NOT NULL,
                         quantidade_disponivel INT NOT NULL DEFAULT 0,
                         quantidade_reservada INT NOT NULL DEFAULT 0,

                         data_atualizacao TIMESTAMP NOT NULL
                                                            DEFAULT CURRENT_TIMESTAMP
                             ON UPDATE CURRENT_TIMESTAMP,

                         CONSTRAINT pk_estoque
                             PRIMARY KEY (id),

                         CONSTRAINT uk_estoque_produto
                             UNIQUE (produto_id),

                         CONSTRAINT fk_estoque_produto
                             FOREIGN KEY (produto_id)
                                 REFERENCES produto(id),

                         CONSTRAINT chk_quantidade_disponivel
                             CHECK (quantidade_disponivel >= 0),

                         CONSTRAINT chk_quantidade_reservada
                             CHECK (quantidade_reservada >= 0)
);


-- =====================================================
-- TABELA MOVIMENTAÇÃO DE ESTOQUE
-- =====================================================

CREATE TABLE movimentacao_estoque (
                                      id BIGINT NOT NULL AUTO_INCREMENT,
                                      produto_id BIGINT NOT NULL,
                                      tipo VARCHAR(20) NOT NULL,
                                      quantidade INT NOT NULL,
                                      referencia VARCHAR(100),
                                      observacao VARCHAR(255),

                                      data_movimentacao TIMESTAMP NOT NULL
                                          DEFAULT CURRENT_TIMESTAMP,

                                      CONSTRAINT pk_movimentacao_estoque
                                          PRIMARY KEY (id),

                                      CONSTRAINT fk_movimentacao_produto
                                          FOREIGN KEY (produto_id)
                                              REFERENCES produto(id),

                                      CONSTRAINT chk_movimentacao_quantidade
                                          CHECK (quantidade > 0),

                                      CONSTRAINT chk_movimentacao_tipo
                                          CHECK (
                                              tipo IN (
                                                       'ENTRADA',
                                                       'SAIDA',
                                                       'RESERVA',
                                                       'CANCELAMENTO'
                                                  )
                                              )
);

