<%-- 
    Document   : cliente
    Created on : 09/08/2014, 16:26:06
    Author     : magdiel-bruno
--%>
<%@page import="br.edu.ifpb.bdnc.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.dao.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Clientes</title>
    <link rel="stylesheet" type="text/css" href="css/css_cliente.css"/>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="dataTables/jquery.dataTables.css">
    <!-- DataTables -->
    <script type="text/javascript" charset="utf8" src="dataTables/jquery.dataTables.js"></script>
    <script>
        $(document).ready(function() {
            $('#clientes').dataTable({
                "language": {
                    "lengthMenu": "Exibir _MENU_ registros por pagina",
                    "zeroRecords": "Nada encontrado",
                    "info": "Mostrando página _PAGE_ de _PAGES_",
                    "infoEmpty": "Sem registros disponíveis",
                    "infoFiltered": "(filtrado de _MAX_ total records)",
                    "search": "Pesquisar:"
                }

            });
        });

        jQuery(document).ready(function($) {
            $('#tabs').tab();
        });
    </script>
</head>
<%
    ClienteDAO clienteDAO = new ClienteDAO();
    List<Cliente> clientes = clienteDAO.listarTodos();
    pageContext.setAttribute("clientes", clientes);
%>
<div class="container">
    <div class="table-responsive">
        <table id="clientes" class="table display">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Rua</th>
                    <th>Bairro</th>
                    <th>Numero</th>
                    <th>CEP</th>
                    <th>Telefone</th>
                    <th>Editar/Excluir</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${clientes}" var="cliente">
                    <tr>
                        <td>${cliente.nome}</td>
                        <td>${cliente.endereco.rua}</td>
                        <td>${cliente.endereco.bairro}</td>
                        <td>${cliente.endereco.numero}</td>
                        <td>${cliente.endereco.cep}</td>
                        <td>(${cliente.telefone.ddd}) ${cliente.telefone.numero}</td>
                        <td>

                            <a href="controlarCliente.jsp?cod=${cliente.codigo}" class="btn pull-left"><span class="glyphicon glyphicon-edit"></span></a>
                            <form id="excluir" method="POST" action="ExcluirCliente">
                                <button type="submit" name="codigo" value="${cliente.codigo}" class="btn"><span class="glyphicon glyphicon-remove"></span></button>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="controlarCliente.jsp" class="btn btn-info">Cadastrar Cliente</a>

    <c:choose>
        <c:when test="${clienteExcluido != null and clienteExcluido == true}">
            <div class="alert alert-dismissable alert-success" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                Cliente excluído
            </div>
        </c:when>
        <c:when test="${clienteExcluido != null and clienteExcluido == false}">
            <div class="alert alert-dismissable alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                Cliente não pôde ser excluído
            </div>
        </c:when>    
    </c:choose>

</div>

</body>
</html>
