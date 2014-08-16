<%-- 
    Document   : controlarCliente
    Created on : 15-Aug-2014, 10:00:02 PM
    Author     : Fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
            <form role="form" class="col-md-6 col-md-offset-3" id="cadastroCliente" method="POST" action="#">
        <div class="form-group">
            <input type="text" name="Nome" class="form-control" placeholder="Nome do Clientes" required>
        </div>
        <div class="form-group">
            <input type="text" name="Rua" class="form-control" placeholder="Rua" required>
        </div>
        <div class="form-group">
            <input type="text" name="Bairro" class="form-control" placeholder="Bairro" required>
        </div>
        <div class="form-group">
            <input type="text" name="Numero" class="form-control" placeholder="Número" required>
        </div>
        <div class="form-group">
            <input type="text" name="Cep" class="form-control" placeholder="CEP" required>
        </div>
        <div class="form-group">
            <input type="text" name="ddd" class="form-control" placeholder="DDD" required>
        </div>
        <div class="form-group">
            <input type="text" name="Numero do Telefone" class="form-control" placeholder="Número de Telefone" required>
        </div>
        <button>
    </form>
    </body>
</html>
