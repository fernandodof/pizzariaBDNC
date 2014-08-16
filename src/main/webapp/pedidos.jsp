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
    </head>
    <body>
        <%
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = produtoDAO.listarTodos();
        %>
        
        <div class="container">
            
        </div>
    </body>
</html>
