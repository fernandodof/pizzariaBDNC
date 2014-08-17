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
	dataPedido timestamp,
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

--Criando visao para produtos vendidos
CREATE OR REPLACE VIEW produtos_vendidos AS
SELECT DISTINCT i.prod.codigo cod FROM pedidos p, table (p.itens) i     

--Inserindo cliente (null para codigo, que será gerado pela sequencia+gatilho)
INSERT INTO clientes VALUES (cliente(null,'Fernando Ferreira', endereco('rua1','bairro 1','numero 1','cep 1'),Telefone_cliente(83,'1234-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Magdiel Ildefonso', endereco('rua 2','bairro 2','numero 2','cep 2'),Telefone_cliente(83,'1234-5793')));   
INSERT INTO clientes VALUES (cliente(null,'José Barros', endereco('rua 3','bairro 3','numero 3','cep 3'),Telefone_cliente(83,'1234-5637')));   
INSERT INTO clientes VALUES (cliente(null,'Antônio', endereco('rua 4','bairro 4','numero 5','cep 5'),Telefone_cliente(83,'1234-2151')));   
INSERT INTO clientes VALUES (cliente(null,'Kelson', endereco('rua 5','bairro 5','numero 5','cep 5'),Telefone_cliente(83,'1805-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Izaquiel', endereco('rua 6','bairro 6','numero 6','cep 6'),Telefone_cliente(83,'1234-5618')));   
INSERT INTO clientes VALUES (cliente(null,'Joelanio', endereco('rua 7','bairro 7','numero 7','cep 7'),Telefone_cliente(83,'1232-5678'))); 
INSERT INTO clientes VALUES (cliente(null,'Marcolina Izabel', endereco('rua 8','bairro 8','numero 8','cep 8'),Telefone_cliente(83,'1234-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Luciana', endereco('rua 9','bairro 9','numero 9','cep 9'),Telefone_cliente(83,'1234-5793')));   
INSERT INTO clientes VALUES (cliente(null,'Elis', endereco('rua 10','bairro 10','numero 10','cep 10'),Telefone_cliente(83,'1234-5637')));   
INSERT INTO clientes VALUES (cliente(null,'Vagner', endereco('rua 11','bairro 11','numero 11','cep 11'),Telefone_cliente(83,'1234-2151')));   
INSERT INTO clientes VALUES (cliente(null,'Claudivan', endereco('rua 12','bairro 12','numero 12','cep 12'),Telefone_cliente(83,'1805-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Filipe', endereco('rua 13','bairro 13','numero 13','cep 13'),Telefone_cliente(83,'1234-5618')));   
INSERT INTO clientes VALUES (cliente(null,'André', endereco('rua 14','bairro 14','numero 14','cep 14'),Telefone_cliente(83,'1232-5678'))); 
INSERT INTO clientes VALUES (cliente(null,'Diego', endereco('rua 15','bairro 15','numero 15','cep 15'),Telefone_cliente(83,'1234-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Valéria', endereco('rua 16','bairro 16','numero 16','cep 16'),Telefone_cliente(83,'1234-5793')));   
INSERT INTO clientes VALUES (cliente(null,'Aristofanio', endereco('rua 17','bairro 17','numero 17','cep 17'),Telefone_cliente(83,'1234-5637')));   
INSERT INTO clientes VALUES (cliente(null,'Ricardo Job', endereco('rua 18','bairro 18','numero 18','cep 18'),Telefone_cliente(83,'1234-2151')));   
INSERT INTO clientes VALUES (cliente(null,'Marciel', endereco('rua 19','bairro 19','numero 19','cep 19'),Telefone_cliente(83,'1805-5678')));   
INSERT INTO clientes VALUES (cliente(null,'Denis', endereco('rua 19','bairro 19','numero 19','cep 19'),Telefone_cliente(83,'1234-5618')));   
INSERT INTO clientes VALUES (cliente(null,'Viviano', endereco('rua 20','bairro 20','numero 20','cep 20'),Telefone_cliente(83,'1232-5678'))); 
INSERT INTO clientes VALUES (cliente(null,'Usain Bolt', endereco('rua 21','bairro 21','numero 21','cep 21'),Telefone_cliente(83,'1232-5678'))); 

--Inserindo produtos
INSERT INTO produtos VALUES (produto(null,'Coca-Cola',4.50));
INSERT INTO produtos VALUES (produto(NULL,'Agua de Côco'2.00));
INSERT INTO produtos VALUES (produto(null,'Guraná Kuat',4.00));
INSERT INTO produtos VALUES (produto(null,'Cajuína São geraldo',4.50));
INSERT INTO produtos VALUES (produto(NULL,'Fanta',4.00));
INSERT INTO produtos VALUES (produto(null,'Sprite',4.50));
INSERT INTO produtos VALUES (produto(null,'Guarani Jesus',4.50));
INSERT INTO produtos VALUES (produto(NULL,'Caipirinha vodca',9.00));
INSERT INTO produtos VALUES (produto(null,'Whisky Red Label',10.00));
INSERT INTO produtos VALUES (produto(null,'Chop pilsen',4.50));
INSERT INTO produtos VALUES (produto(NULL,'Tequila',4.00));

--Inserindo pizzas
INSERT INTO produtos VALUES (pizza(null,'Brasileira',42,'Mussarela, presuto requeijão cremoso e azeitonas'));
INSERT INTO produtos VALUES (pizza(NULL,'Supreme',42,'Carnes bovina e suína, pepperoni, mussarela, champignon, pimentão e cebola'));
insert into PRODUTOS values (PIZZA(null,'Calabresa',38,'Mussarela, presunto, cebola e azeitonas'));
INSERT INTO produtos VALUES (pizza(null,'Corn$Bacon',30,'Musarela, milho e bacon'));
INSERT INTO produtos VALUES (pizza(NULL,'Vegetariana',40,'CMussarela, champignon, pimentão, cebola, tomate e azeitonas'));
INSERT INTO PRODUTOS values (PIZZA(null,'Pepperoni',20,'Mussarela e fatias de pepperoni (salame especial condimentado com páprica)'));
INSERT INTO produtos VALUES (pizza(null,'Mussarela',30,'A mais pura e saborosa das mussarelas'));
INSERT INTO produtos VALUES (pizza(NULL,'Frango Supreme',40,'Mussarela, pepperoni, frango, champingnon, pimentão e cebola'));
INSERT INTO PRODUTOS values (PIZZA(null,'Pepperoni e Cheddar',20,'Mussarela, cebola, pepperoni, tiras de cheddar Sadia e azeitonas'));
INSERT INTO produtos VALUES (pizza(null,'Super Brasilerra',30,'Queijo, presunto, calabresa, tomate em cubos, tiras de queijo Philadelphia e azeitonas'));

COMMIT;