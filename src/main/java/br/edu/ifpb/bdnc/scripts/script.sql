--criando o tipo endereco
CREATE OR REPLACE TYPE endereco AS OBJECT(
	rua VARCHAR(100),
	bairro VARCHAR(100),
	numero VARCHAR(10),
	cep VARCHAR(10)
);

--criando tipo telefone
create or replace type telefone_cliente as object(
        ddd number,
        numero varchar(10)
);

--criando tipo cliente
CREATE OR REPLACE TYPE cliente AS OBJECT(
	codigo INT,
	nome VARCHAR(100),
	ende endereco,
	tel telefone_cliente
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

--criando tipo produto
CREATE OR REPLACE TYPE produto AS OBJECT(
	codigo int,
	nome VARCHAR(100),
	preco NUMBER
)not final;

--criando tipo pizza que herda de produto
CREATE OR REPLACE TYPE pizza UNDER produto(
	ingredientes VARCHAR(200)
);

--criando tabela para produtos
CREATE TABLE produtos OF produto;

--criando squencia para produto
CREATE SEQUENCE produto_seq START WITH 1 INCREMENT BY 1 nomaxvalue; 

---criando gatilho para o codigo do produto
CREATE TRIGGER produto_trigger
BEFORE INSERT ON produtos
FOR EACH ROW
   BEGIN
     SELECT produto_seq.NEXTVAL INTO :new.codigo FROM dual;
   END;

--criando tipo itemPedido
CREATE OR REPLACE TYPE itemPedido AS OBJECT(
	codigo int,
	prod REF produto,
	quantidade int,
	preco NUMBER
);

--criando lista dinamica para pedidos
CREATE OR REPLACE TYPE itensPedido AS TABLE OF itemPedido;

--criando tipo pedido
CREATE OR REPLACE TYPE pedido AS OBJECT(
	codigo INT,
	dataPedido date,
	cli REF cliente,
	itens itensPedido
);

--Criando tabela para pedidos
CREATE TABLE pedidos OF pedido (
  codigo PRIMARY KEY,
  dataPedido NOT NULL,
  cli WITH ROWID REFERENCES clientes
) NESTED TABLE itens STORE as itens_pd;

--criando squencia para pedido
CREATE SEQUENCE pedido_seq START WITH 1 INCREMENT BY 1 nomaxvalue; 

---criando gatilho para o codigo do pedido
CREATE OR REPLACE TRIGGER pedido_trigger
BEFORE INSERT ON pedidos
FOR EACH ROW
   BEGIN
     SELECT pedido_seq.NEXTVAL INTO :new.codigo FROM dual;
   END;
   
CREATE OR REPLACE VIEW produtos_vendidos AS
SELECT DISTINCT i.prod.codigo cod FROM pedidos p, table (p.itens) i  