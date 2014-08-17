/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.servlets;

import br.edu.ifpb.bdnc.beans.ItemPedido;
import br.edu.ifpb.bdnc.beans.Pedido;
import br.edu.ifpb.bdnc.beans.Produto;
import br.edu.ifpb.bdnc.dao.ClienteDAO;
import br.edu.ifpb.bdnc.dao.PedidoDAO;
import br.edu.ifpb.bdnc.dao.ProdutoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando
 */
@WebServlet(name = "CadastrarPedido", urlPatterns = {"/CadastrarPedido"})
public class CadastrarPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cliente = request.getParameter("cliente");
        Pedido pedido = new Pedido();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        try {
            if (cliente.equals("pizzaria")) {
                pedido.setCli(null);
            } else {
                pedido.setCli(clienteDAO.getClientePorCodigo(Integer.parseInt(cliente)));

            }

            List<Integer> codigosProdutos = new ArrayList();
            List<Integer> quantidadesProdutos = new ArrayList();
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 1; i <= count; i++) {
                codigosProdutos.add(Integer.parseInt(request.getParameter("field" + i)));
                quantidadesProdutos.add(Integer.parseInt(request.getParameter("qtde" + i)));
            }

            List<ItemPedido> itensPedidos = new ArrayList();

            Produto produto = new Produto();
            for (int i = 1; i <= count; i++) {
                produto = produtoDAO.listarProdutoPorCodigo(codigosProdutos.get(i - 1));
                ItemPedido itemPedido = new ItemPedido();
                itemPedido.setPreco(produto.getPreco());
                itemPedido.setProd(produto);
                itemPedido.setQuantidade(quantidadesProdutos.get(i - 1));
                itensPedidos.add(itemPedido);
            }
            
            pedido.setItens(itensPedidos);
            Calendar d = new GregorianCalendar();
            Timestamp dt = new Timestamp(d.getTimeInMillis());
            pedido.setData(dt);

            pedidoDAO.persistir(pedido);

        } catch (NullPointerException | SQLException ex) {
            request.setAttribute("pedidoCadastrado", false);
            request.getRequestDispatcher("controlarPedido.jsp").forward(request, response);
        }
        request.setAttribute("pedidoCadastrado", true);
        response.sendRedirect("pedidos.jsp");
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
