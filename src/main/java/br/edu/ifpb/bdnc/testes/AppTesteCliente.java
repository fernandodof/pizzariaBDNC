/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.testes;

import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Endereco;
import br.edu.ifpb.bdnc.beans.Telefone;
import br.edu.ifpb.bdnc.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.TestCase.assertNotNull;

/**
 *
 * @author magdiel-bruno
 */
public class AppTesteCliente {
    public static void main(String[] args) {
        ClienteDAO cdb = new ClienteDAO();

        //endereço
        Endereco e = new Endereco();
        e.setBairro("Centro 5");
        e.setCep("84348-222");
        e.setNumero("224");
        e.setRua("Harry Potter");

        //telefone
        Telefone t = new Telefone();
        t.setDdd(83);
        t.setNumero("22432-0909");

        //Cliente
        Cliente c = new Cliente();
        c.setNome("Ronaldo");
        c.setEndereco(e);
        c.setTelefone(t);
        
            
        try {            
            //Persistindo
            //cdb.persistir(c);
            
            //Atualizando
            //c.setCodigo(5);
            //cdb.atualizar(c);
            
            //Deletando
            //cdb.excluir(3);
            
            //Listando
            List<Cliente> clientes = cdb.listarTodos();
            for (Cliente cl : clientes) {
                System.out.println("Clientes: "+cl);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AppTesteCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
