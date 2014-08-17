<%-- 
    Document   : newjsp
    Created on : 16-Aug-2014, 9:20:14 PM
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
        <div class="container">
            <form role="form" class="col-md-1 col-md-offset-3">
                <div class="form-group">
                    <select name="cliente">
                        <option value="pizzaria">Pedido na pizzaria</option>
                        <option value="14">Magdiel Ildefonso</option>

                    </select>
                    <div class="row">
                        <input type="hidden" name="count" value="1" />
                        <div class="control-group" id="fields">
                            <label class="control-label" for="field1">Item</label>
                            <div class="controls" id="profs"> 
                                <form class="input-append">
                                    <div id="field"><input autocomplete="off" class="input" id="field1" name="prof1" type="text" placeholder="Type something" data-items="8"/><button id="b1" class="btn add-more" type="button">+</button></div>
                                </form>
                                <small>Pressione para adicionar mais itens</small>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
