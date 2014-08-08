--criando o tipo endereco
CREATE OR REPLACE TYPE endereco AS OBJECT(
	rua VARCHAR(100),
	bairro VARCHAR(100),
	numero VARCHAR(10),
	cep VARCHAR(10)
);

--criando tipo telefone //MBMI
create or replace type telefone as object(
        ddd number,
        numero varchar(10)
);

--criando tipo cliente
CREATE OR REPLACE TYPE cliente AS OBJECT(
	codigo INT,
	nome VARCHAR(100),
	ende endereco,
	tel telefone,
);

--criando tabela para clientes
CREATE TABLE clientes of cliente;

--criando squencia para clientes
CREATE SEQUENCE cliente_seq START WITH 1 INCREMENT BY 1 nomaxvalue; 

---criando gatilho para o codigo do cliente
CREATE TRIGGER cliente_trigger
BEFORE INSERT ON clientes
FOR EACH ROW
   BEGIN
     SELECT cliente_seq.NEXTVAL INTO :new.codigo FROM dual;
   END;

--------------
--Inserindo cliente (null para codigo, que será gerado pela sequencia+gatilho)
INSERT INTO clientes VALUES (cliente(null,'Cliente 1', endereco('rua 1','bairro 1','numero 1','cep 1'),'(83)1234-5678'));   
   
