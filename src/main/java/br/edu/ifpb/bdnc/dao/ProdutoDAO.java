/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.banco.Oracle;
import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Endereco;
import br.edu.ifpb.bdnc.beans.Produto;
import br.edu.ifpb.bdnc.beans.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author magdiel-bruno
 */
public class ProdutoDAO {
    public void persistir(Produto p) throws SQLException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "insert into clientes values(?)";

        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, p);
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public void atualizar(Produto p) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "update produtos p set value(p) = ? where p.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, p);
            pstmt.setObject(2, p.getCodigo());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public void excluir(int codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "delete produtos p where p.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public List<Produto> listarTodos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select value(p) from produtos p order by p.nome";
        try {
            connection = Oracle.getConnection();
//            Map<String, Class<?>> map = connection.getTypeMap();
//            map.put("CLIENTE", Cliente.class);
//            map.put("ENDERECO", Endereco.class);
//            map.put("TELEFONE_CLIENTE", Telefone.class);

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = (Produto) rs.getObject(1);
                produtos.add(produto);
            }
            rs.close();

            return produtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Oracle.close(stmt);
            Oracle.close(connection);
        }
    }
}
