/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.testes;

import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.ItemPedido;
import br.edu.ifpb.bdnc.beans.Pedido;
import br.edu.ifpb.bdnc.beans.Produto;
import br.edu.ifpb.bdnc.dao.ClienteDAO;
import br.edu.ifpb.bdnc.dao.PedidoDAO;
import br.edu.ifpb.bdnc.dao.ProdutoDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author magdiel-bruno
 */
public class AppTestePedido {
    public static void main(String[] args) {
        PedidoDAO pdb = new PedidoDAO();
        ProdutoDAO pro = new ProdutoDAO();
        ClienteDAO cl = new ClienteDAO();
        
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido item = new ItemPedido();
        
        Produto p;
        Cliente c;
        
        
        try {
            p = pro.listarProdutoPorCodigo(1);
            Produto p2 = new  Produto();
            p2 = pro.listarProdutoPorCodigo(3);
            //System.out.println("Produto: "+p);
            c = cl.getClientePorCodigo(1);
            //System.out.println("Cliente: "+c);
            
            
            item.setProd(p);
            item.setPreco(p.getPreco());
            item.setQuantidade(2);
            
            ItemPedido item2 = new ItemPedido();
            item2.setProd(p2);
            item2.setPreco(p2.getPreco());
            item2.setQuantidade(1);
            
            //System.out.println("Item: "+item);
            itens.add(item);
            itens.add(item2);
            
            
            Pedido ped = new Pedido();
            
            ped.setItens(itens);
            ped.setCli(c);
            Calendar d = new GregorianCalendar();
            Timestamp dt = new Timestamp(d.getTimeInMillis());
            ped.setData(dt);
            
            //pdb.persistir(ped);
            //pdb.excluir(3);
            
            List<Pedido> ps = pdb.listarTodos();
            for (Pedido pd : ps) {
                System.out.println("Pedidos: " + pd);
            }
        
            //System.out.println("Teste produto: "+pro.verificaVendido(3));
        } catch (SQLException ex) {
            Logger.getLogger(AppTestePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
    
}
