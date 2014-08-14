<%-- 
    Document   : EditarProduto
    Created on : 14-Aug-2014, 12:28:52 PM
    Author     : Fernando
--%>
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
            int cod = Integer.parseInt(request.getParameter("cod"));
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.listarProdutoPorCodigo(cod);

            pageContext.setAttribute("produto", produto);

        %>

        <div class="container">
            <c:choose>
                <c:when test="${produto != null}">
                    <form class="form-group editar col-md-6 col-md-offset-3" method="POST" action="EditarProduto">
                        <input type="hidden" name="codigo" value="${produto.codigo}">
                        <div class="form-group">
                            <input type="text" name="nomeProduto" value="${produto.nome}" class="form-control" placeholder="Nome do produto" required>
                        </div>
                        <c:if test="${produto.getClass().getSimpleName() eq 'Pizza'}">
                            <div class="form-group">
                                <input type="text" name="ingredientesProduto" value="${produto.ingredientes}" class="form-control" placeholder="Ingredientes para a pizza" required>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <input type="text" name="precoProduto" value="${produto.preco}" class="form-control" placeholder="Preco do produto" 
                                   required>
                        </div>
                        <button type="submit" class="btn btn-success pull-right">Editar</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12"><p id="naoEncontrado">Produto não encontrado</p></div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
