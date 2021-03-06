/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.servlets;

import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Endereco;
import br.edu.ifpb.bdnc.beans.Telefone;
import br.edu.ifpb.bdnc.dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Fernando
 */
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Cliente cliente = new Cliente();
        cliente.setNome(request.getParameter("nome"));
        //telefone
        Telefone telefone = new Telefone();
        telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
        telefone.setNumero(request.getParameter("numeroDoTelefone"));
        cliente.setTelefone(telefone);
        //endereco
        Endereco endereco = new Endereco();
        endereco.setRua(request.getParameter("rua"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setNumero(request.getParameter("numero"));
        endereco.setCep(request.getParameter("cep"));
        cliente.setEndereco(endereco);

        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            if (request.getParameter("botao") != null && request.getParameter("botao").equals("cadastrar")) {
                clienteDAO.persistir(cliente);
            }else{
                cliente.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                clienteDAO.atualizar(cliente);
            }
            request.setAttribute("sucesso", true);
            request.getRequestDispatcher("controlarCliente.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("sucesso", false);
            request.getRequestDispatcher("controlarCliente.jsp").forward(request, response);
        }

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
