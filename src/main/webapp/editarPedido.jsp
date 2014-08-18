<%-- 
    Document   : editarPedido
    Created on : 17/08/2014, 18:45:15
    Author     : magdiel-bruno
--%>

<%@page import="br.edu.ifpb.bdnc.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.dao.ClienteDAO"%>
<%@page import="br.edu.ifpb.bdnc.dao.ClienteDAO"%>
<%@page import="br.edu.ifpb.bdnc.beans.Pedido"%>
<%@page import="br.edu.ifpb.bdnc.dao.PedidoDAO"%>
<%@page import="br.edu.ifpb.bdnc.beans.Produto"%>
<%@page import="br.edu.ifpb.bdnc.dao.ProdutoDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/css_editarProduto.css" rel="stylesheet">
    </head>
    <body>
        <%
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Cliente> clientes = clienteDAO.listarTodos();
            pageContext.setAttribute("clientes", clientes);
        %>
        <%
            if (request.getParameter("cod") != null) {
                int cod = Integer.parseInt(request.getParameter("cod"));
                PedidoDAO pedidoDAO = new PedidoDAO();
                Pedido pedido = pedidoDAO.listarPedidoPorCodigo(cod);
                
                pageContext.setAttribute("pedido", pedido);
                pageContext.setAttribute("cod", request.getParameter("cod"));
            }
        %>

        <div class="container">
            <h1>Pedido</h1>
            <form class="col-md-6 col-md-offset-3" id="consulta" action="editarPedido.jsp">
                <label for="cod">Informe o código do pedido:</label>
                <div class="form-group">
                    <input type="text" name="cod" id="cod" class="form-control" placeholder="Codigo do Pedido" required value="${cod}">
                </div>
                <button type="submit" class="btn btn-success pull-right" name="botao">Consultar</button>
            </form>
                    
            <c:choose>
                <c:when test="${pedido != null}">
                    <form class="form-group editar col-md-6 col-md-offset-3" method="POST" action="EditarPedido">
                        <input type="hidden" name="codigo" value="${pedido.codigo}">
                        
                            <select name="cliente" class="input-sm col-xs-12" id="slCliente">
                                <option value="${pedido.cli.codigo}">${pedido.cli.nome}</option>
                                <option value="pizzaria">Pedido na pizzaria</option>
                                <c:forEach items="${clientes}" var="cliente">
                                    <option value="${cliente.codigo}">${cliente.nome}</option>
                                </c:forEach>
                            </select>
                        <button type="submit" class="btn btn-success pull-right">Editar</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12"><p id="naoEncontrado">Pedido não encontrado</p></div>
                </c:otherwise>
            </c:choose>
                    
        </div>
    </body>
</html>
