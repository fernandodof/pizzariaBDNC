<%-- 
    Document   : controlarPedidio
    Created on : 16-Aug-2014, 8:10:16 PM
    Author     : Fernando
--%>
<%@page import="br.edu.ifpb.bdnc.beans.Cliente"%>
<%@page import="br.edu.ifpb.bdnc.dao.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/css_controlaPedido.css" rel="stylesheet">

        <script>
            $(document).ready(function() {
                var next = 1;
                $(".add-more").click(function(e) {
                    e.preventDefault();
                    var addto = "#qtde" + next;
                    next = next + 1;
                    var newIn = '<input autocomplete="off" class="input" placeholder="Codigo do Produto" id="field' + next + '" name="field' + next + '" type="text" required>';
                    var newInput = $(newIn);

                    var newIn1 = '<input autocomplete="off" class="input" placeholder="Quantidade" id="qtde' + next + '" name="qtde' + next + '" type="text" required>';
                    var newInput1 = $(newIn1);

                    $(addto).after(newInput1);
                    $(addto).after(newInput);

                    $("#field" + next).attr('data-source', $(addto).attr('data-source'));
                    $("#qtde" + next).attr('data-source', $(addto).attr('data-source'));
                    $("#count").val(next);

                });
                document.getElementById('#count').innerHTML = next;
            });
        </script>
    </head>

    <%
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listarTodos();
        pageContext.setAttribute("clientes", clientes);
    %>

    <body>
        <div class="container">
            <form role="form" class="col-md-1 col-md-offset-3 pull-left" method="POST" action="CadastrarPedido">
                <div class="form-group">
                    <select name="cliente">
                        <option value="pizzaria">Pedido na pizzaria</option>
                        <c:forEach items="${clientes}" var="cliente">
                            <option value="${cliente.codigo}">${cliente.nome}</option>
                        </c:forEach>
                    </select>
                    <div class="row">
                        <input type="hidden" name="count" value="1" id="count"/>
                        <div class="control-group" id="fields">
                            <label class="control-label" for="field1">Produtos</label>
                            <div class="controls form-group" id="profs"> 
                                <div id="div">
                                    <input autocomplete="off" class="input" id="field1" name="field1" type="text" placeholder="Codigo Produto" data-items="8" required/>
                                    <input autocomplete="off" class="input" id="qtde1" name="qtde1" type="text" placeholder="Quantidade" data-items="8" required/>
                                    <button id="b1" class="btn add-more" type="button">+</button>
                                </div>
                                <small>+ Itens</small>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-success">Cadastrar</button>

            </form>
            <div class="row">
                <c:if test="${pedidoCadastrado != null}">
                    <c:choose>
                        <c:when test="${pedidoCadastrado == true}">
                            <div class="alert alert-dismissable alert-success pull-left erro" role="alert">
                                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                Pedido feito com sucesso
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-dismissable alert-danger pull-left erro" role="alert">
                                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                Pedido não pôde ser cadastrado
                            </div>
                        </c:otherwise>    
                    </c:choose>
                </c:if>
            </div>
        </div>
    </body>
</html>
