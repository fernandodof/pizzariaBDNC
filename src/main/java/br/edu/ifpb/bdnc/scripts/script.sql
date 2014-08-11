--criando o tipo endereco
CREATE OR REPLACE TYPE endereco AS OBJECT(
	rua VARCHAR(100),
	bairro VARCHAR(100),
	numero VARCHAR(10),
	cep VARCHAR(10)
);

--criando tipo telefone //MBMI
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

--Inserindo cliente (null para codigo, que será gerado pela sequencia+gatilho)
INSERT INTO clientes VALUES (cliente(null,'Cliente 1', endereco('rua 1','bairro 1','numero 1','cep 1'),Telefone_cliente(83,'1234-5678')));   

-----------
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

--Inserindo produtos
INSERT INTO produtos VALUES (produto(null,'Coca-Cola 2l',4.50));
INSERT INTO produtos VALUES (produto(null,'Fanta 1l',3.00));

--Inserindo pizzas
INSERT INTO produtos VALUES (pizza(null,'Portuguesa Média',30,'Cebola ,Ovo, Presunto e Mussarela'));
INSERT INTO produtos VALUES (pizza(null,'Calabresa Grande',40,'Calabresa, Presunto, Mussarela, Tomate'));

--Recuperando todos os produtos
SELECT * FROM PRODUTOS

--Recuperando apenas pizzas
SELECT p.codigo, p.nome, p.preco, TREAT(VALUE(p) AS pizza).ingredientes ingredientes
FROM produtos p WHERE VALUE(p) IS OF (ONLY pizza);


------------   
--criando tipo itemPedido   
CREATE OR REPLACE TYPE itemPedido AS OBJECT(
	codigo int,
	prod REF produto,
	quantidade int,
	preco NUMBER
);

--criando lista dinamica para pedidos
CREATE OR REPLACE TYPE itensPedido AS TABLE OF itemPedido;
------------

--criando tipo pedido 
CREATE OR REPLACE TYPE pedido AS OBJECT(
	codigo int,
	dataPedido date,
	cli REF cliente,
	itens itensPedido
);

--Criando tabela para pedidos
CREATE TABLE pedidos OF pedido NESTED TABLE itens STORE as itens_pd;

--criando squencia para pedido
CREATE SEQUENCE pedido_seq START WITH 1 INCREMENT BY 1 nomaxvalue; 

---criando gatilho para o codigo do pedido
CREATE TRIGGER pedido_trigger
BEFORE INSERT ON pedidos
FOR EACH ROW
   BEGIN
     SELECT pedido_seq.NEXTVAL INTO :new.codigo FROM dual;
   END;
   
--inserindo pedidos

INSERT INTO pedidos VALUES (null,to_date(sysdate,'DD/MM/YYYY'), (SELECT REF(c) FROM clientes c WHERE c.codigo = 1), 
itensPedido(itemPedido(null,(SELECT REF(p) FROM produtos p WHERE p.codigo = 1), 1, 4.50),
            itemPedido(null,(SELECT REF(p) FROM produtos p WHERE p.codigo = 4), 1, 40))); 

--Recuperando produtos
SELECT p.codigo, p.dataPedido, p.cli.nome, i.prod.nome FROM pedidos p, table (p.itens) i