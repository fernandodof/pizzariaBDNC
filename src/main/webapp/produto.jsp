<%-- 
    Document   : produto
    Created on : 12-Aug-2014, 1:44:28 PM
    Author     : Fernando
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.edu.ifpb.bdnc.dao.ProdutoDAO"%>
<%@page import="br.edu.ifpb.bdnc.beans.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="css/css_produto.css">
        <link rel="stylesheet" type="text/css" href="dataTables/jquery.dataTables.css">
        <!-- DataTables -->
        <script type="text/javascript" charset="utf8" src="dataTables/jquery.dataTables.js"></script>
        <script>
            $(document).ready(function() {
                $('#produtos').dataTable({
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
    <%
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarTodos();
        pageContext.setAttribute("produtos", produtos);
    %>
    <div class="container">
        <div class="table-responsive">
            <table id="produtos" class="table display">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Venda</th>
                        <th>Custo</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${produtos}" var="produto">
                        <tr>
                            <td>${produto.codigo}</td>
                            <td>${produto.nome}</td>
                            <td>R$ ${produto.preco}</td>
                            <c:choose>
                                <c:when test="${produto.getClass().getSimpleName() eq 'Produto'}">
                                    <td>R$ ${produto.preco*0.5}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>R$ ${produto.preco*0.6}</td>
                                </c:otherwise>
                            </c:choose>
                            <td><a href="EditarProduto.jsp?cod=${produto.codigo}" class="btn"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td><form id="excluir" method="POST" action="ExcluirProduto">
                                    <button type="submit" name="codigo" value="${produto.codigo}" class="btn"><span class="glyphicon glyphicon-remove"></span></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${sucesso != null and bebida != null}">
            <c:choose>
                <c:when test="${sucesso == false}">
                    <div class="alert alert-dismissable alert-success" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        Bebida cadastrada com sucesso
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-dismissable alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        Bebida não pôde ser cadastrada
                    </div>
                </c:otherwise>    
            </c:choose>
        </c:if>

        <c:if test="${sucesso != null and pizza !=null}">
            <c:choose>
                <c:when test="${sucesso == false}">
                    <div class="alert alert-dismissable alert-success" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        Pizza cadastrada com sucesso
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-dismissable alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        Pizza não pôde ser cadastrada
                    </div>
                </c:otherwise>    
            </c:choose>
        </c:if>

        <c:choose>
            <c:when test="${produtoExcluido != null and produtoExcluido == true}">
                <div class="alert alert-dismissable alert-success" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    Produto excluído com sucesso
                </div>
            </c:when>
            <c:when test="${produtoExcluido != null and produtoExcluido == false}">
                <div class="alert alert-dismissable alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    Produto não pôde ser excluído
                </div>
            </c:when>    
        </c:choose>

        <div id="content">
            <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
                <li class="active"><a href="#bedidas" data-toggle="tab">+ Bebidas</a></li>
                <li><a href="#pizzas" data-toggle="tab">+ Pizzas</a></li>
            </ul>
            <div id="my-tab-content" class="tab-content">
                <div class="tab-pane active" id="bedidas">
                    <form role="form" class="col-lg-6" method="POST" action="CadastrarProduto">
                        <p>Insira as informações sobre a bebida</p>
                        <div class="form-group">
                            <!--oninvalid="this.setCustomValidity('Informe o nome do produto')-->
                            <input type="text" name="nomeProduto" class="form-control" placeholder="Nome do produto" required>
                        </div>
                        <div class="form-group">
                            <input type="text" name="precoProduto" class="form-control" placeholder="Preco do produto" 
                                   required pattern="^\d+(\.|\,)\d{2}$">
                        </div>
                        <button type="submit" class="btn btn-success pull-right">Cadastrar</button>
                    </form>
                </div>
                <div class="tab-pane" id="pizzas">
                    <form role="form" class="col-lg-6" method="POST" action="CadastrarProduto">
                        <p>Insira as informações sobre a pizza</p>
                        <div class="form-group">
                            <input type="text" name="nomeProduto" class="form-control" placeholder="Nome da Pizza" required>
                        </div>
                        <div class="form-group">
                            <input type="text" name="ingredientesProduto" class="form-control" placeholder="Ingredientes para a pizza" required>
                        </div>
                        <div class="form-group">
                            <input type="text" name="precoProduto" class="form-control" placeholder="Preco do produto" 
                                   required pattern="^\d+(\.|\,)\d{2}$">
                        </div>
                        <button type="submit" class="btn btn-success pull-right">Cadastrar</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
