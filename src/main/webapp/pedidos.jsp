<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.edu.ifpb.bdnc.beans.Pedido"%>
<%@page import="br.edu.ifpb.bdnc.dao.PedidoDAO"%>
<%@page import="br.edu.ifpb.bdnc.dao.ProdutoDAO"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifpb.bdnc.beans.Produto"%>
<%@page import="br.edu.ifpb.bdnc.beans.Produto"%>
<%@include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="dataTables/jquery.dataTables.css">
        <!-- DataTables -->
        <script type="text/javascript" charset="utf8" src="dataTables/jquery.dataTables.js"></script>
        <script>
            $(document).ready(function() {
                $('#pedidos').dataTable({
                    "order": [[1, "asc"]],
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
    <body>
        <%
            PedidoDAO pedidoDAO = new PedidoDAO();
            List<Pedido> pedidos = pedidoDAO.listarTodos();
            pageContext.setAttribute("pedidos", pedidos);
        %>

        <div class="container">
            <div class="table-responsive">
                <table id="pedidos" class="table display">
                    <thead>
                        <tr>
                            <th>Codigo</th>
                            <th>Data</th>
                            <th>Cliente</th>
                        </tr>
                    </thead>    
                    <tbody>
                        <c:forEach items="${pedidos}" var="pedido">
                            <tr>
                                <td>${pedido.codigo}</td>
                                <td>${pedido.data}</td>
                                <td>${pedido.cli.nome}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="controlarPedidio.jsp.jsp" class="btn btn-info">Novo pedido</a>
        </div>
    </body>
</html>
