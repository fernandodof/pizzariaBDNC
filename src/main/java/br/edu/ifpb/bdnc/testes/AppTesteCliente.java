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
        e.setBairro("Centro 2");
        e.setCep("89898-222");
        e.setNumero("222");
        e.setRua("Severo Snape");

        //telefone
        Telefone t = new Telefone();
        t.setDdd(83);
        t.setNumero("2222-0909");

        //Cliente
        Cliente c = new Cliente();
        c.setNome("Magno");
        c.setEndereco(e);
        c.setTelefone(t);
        c.setCodigo(1);
        try {
            //Persistindo
            //cdb.persistir(c);
            
            //Atualizando
            //cdb.atualizar(c);
            
            //Deletando
            cdb.excluir(1);
            
            //Listando
            List<Cliente> clientes = cdb.listarTodos();
            if(clientes == null){
                System.out.println("Tabela Vazia");
            }else{
                System.out.println(clientes);
            }

        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
