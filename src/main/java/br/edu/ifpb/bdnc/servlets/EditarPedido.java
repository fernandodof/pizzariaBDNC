/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.servlets;

import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Pedido;
import br.edu.ifpb.bdnc.dao.ClienteDAO;
import br.edu.ifpb.bdnc.dao.PedidoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author magdiel-bruno
 */
@WebServlet(name = "EditarPedido", urlPatterns = {"/EditarPedido"})
public class EditarPedido extends HttpServlet {

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
        int cod = Integer.parseInt(request.getParameter("codigo"));
        String cliente = request.getParameter("cliente");
        
        ClienteDAO cl = new ClienteDAO();
        PedidoDAO pd = new PedidoDAO();
        Pedido p = new Pedido();
        Cliente c = new Cliente();
        
        try {
            p = pd.listarPedidoPorCodigo(cod);
            
            if (cliente.equals("pizzaria")) {
                p.setCli(null);
            } else {
                p.setCli(cl.getClientePorCodigo(Integer.parseInt(cliente)));
            }
            
            //System.out.println(p.getCli().getNome());
            
            pd.atualizar(p);
        } catch (SQLException ex) {
            Logger.getLogger(EditarPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
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
