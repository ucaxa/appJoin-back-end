CREATE TABLE produtos
(
    id                      bigint(20) NOT NULL AUTO_INCREMENT,
    nome                    varchar(255)   NOT NULL,
    valor                   decimal(38, 2) NOT NULL,
    data_cadastro           datetime       NOT NULL,
    data_ultima_atualizacao datetime DEFAULT NULL,
    categoria_id            bigint(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_id) REFERENCES categorias (id)
)