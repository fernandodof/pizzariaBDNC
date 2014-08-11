/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.testes;

import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Produto;
import br.edu.ifpb.bdnc.dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author magdiel-bruno
 */
public class AppTesteProduto {
    public static void main(String[] args) {
        ProdutoDAO pdb = new ProdutoDAO();
        Produto p = new Produto();
        
        p.setNome("Sprite 1l");
        p.setPreco(5.5f);
        try {
            //pdb.persistir(p);
            List<Produto> produtos = pdb.listarTodos();
            for (Produto ps : produtos) {
                System.out.println("Produtos: " + ps);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppTesteProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
