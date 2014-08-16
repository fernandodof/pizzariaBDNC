<%-- 
    Document   : controlarCliente
    Created on : 15-Aug-2014, 10:00:02 PM
    Author     : Fernando
--%>
<%@page import="br.edu.ifpb.bdnc.beans.Cliente"%>
<%@page import="br.edu.ifpb.bdnc.dao.ClienteDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link href="css/css_controlaCliente.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (request.getParameter("cod") != null) {
                int cod = Integer.parseInt(request.getParameter("cod"));
                ClienteDAO produtoDAO = new ClienteDAO();
                Cliente cliente = produtoDAO.getClientePorCodigo(cod);

                pageContext.setAttribute("cliente", cliente);
            }else{
                pageContext.setAttribute("form", true);
            }
        %>
        <div class="container">    
            <h1>Cliente</h1>
            <c:choose>
                <c:when test="${cliente != null or form}">
                    <form role="form" class="col-md-6 col-md-offset-3" id="cadastroCliente" method="POST" action="CadastrarCliente">
                        <div class="form-group">
                            <input type="text" name="nome" class="form-control" placeholder="Nome do Cliente" required value="${cliente.nome}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="rua" class="form-control" placeholder="Rua" required value="${cliente.endereco.rua}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="bairro" class="form-control" placeholder="Bairro" required value="${cliente.endereco.bairro}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="numero" class="form-control" placeholder="Número" required value="${cliente.endereco.numero}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="cep" class="form-control" placeholder="CEP" required value="${cliente.endereco.cep}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="ddd" class="form-control" placeholder="DDD" required value="${cliente.telefone.ddd}">
                        </div>
                        <div class="form-group">
                            <input type="text" name="numeroDoTelefone" class="form-control" maxlength="10" placeholder="Número de Telefone" required value="${cliente.telefone.numero}">
                        </div>
                        <input type="hidden" name="codigo" value="${cliente.codigo}">
                        <c:choose>
                            <c:when test="${cliente == null}">
                                <button type="submit" class="btn btn-success pull-right" value="cadastrar" name="botao">Cadastrar</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-success pull-right" value="editar" name="botao">Editar</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-12"><p id="naoEncontrado">Cliente não encontrado</p></div>
                </c:otherwise>
            </c:choose>

            <c:if test="${sucesso != null}">

                <c:choose>
                    <c:when test="${sucesso == false}">
                        <div class="alert alert-dismissable alert-danger col-md-6 col-md-offset-3 pull-left" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            Operação não põde ser realizada
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-dismissable alert-success col-md-6 col-md-offset-3 pull-left" role="alert">
                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            Operaçao realizada com sucesso 
                        </div>
                    </c:otherwise>    
                </c:choose>
            </c:if>

        </div>
    </body>
</html>
