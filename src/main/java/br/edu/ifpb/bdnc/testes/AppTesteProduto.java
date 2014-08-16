/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.testes;

import br.edu.ifpb.bdnc.beans.Pizza;
import br.edu.ifpb.bdnc.beans.Produto;
import br.edu.ifpb.bdnc.dao.PizzaDAO;
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
        Pizza pz = new Pizza();
        PizzaDAO pzdb = new PizzaDAO();
        
        pz.setNome("Quatro Queijos");
        //p.setCodigo(6);
        pz.setPreco(5.5f);
        pz.setIngredientes("Queijo, queijo, queijo e queijo");
        try {
            //pdb.persistir(pz);
            //pdb.atualizar(p);
            //pdb.excluir(8);
            //System.out.println(pdb.listarProdutoPorCodigo(7));
            List<Produto> produtos = pdb.listarTodos();
            for (Produto ps : produtos) {
                System.out.println("Codigo: "+ps.getCodigo()+" - Nome: "+ps.getNome());
            }
            
            List<Pizza> pizzas = pzdb.listarTodos();
            for (Pizza pzs : pizzas) {
                System.out.println("PIZZAS: "+pzdb.listarTodos());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AppTesteProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
