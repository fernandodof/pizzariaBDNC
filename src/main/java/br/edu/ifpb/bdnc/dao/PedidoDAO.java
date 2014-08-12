/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.banco.Oracle;
import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Endereco;
import br.edu.ifpb.bdnc.beans.ItemPedido;
import br.edu.ifpb.bdnc.beans.Pedido;
import br.edu.ifpb.bdnc.beans.Pizza;
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
 * @author Fernando
 */
public class PedidoDAO {
        public void persistir(Pedido p) throws SQLException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO pedidos VALUES(?)";

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

    public void atualizar(Pedido p) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "UDATE PEDIDOS p SET VALUE(p) = ? WEHRE p.codigo = ?";
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
        String sql = "DELETE FROM PEDIDOS p WHERE p.codigo = ?";
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

    public List<Pedido> listarTodos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "SELECT VALUE(p) from pedidos p ORDER BY P.dataPedido";
        try {
            connection = Oracle.getConnection();
            Map<String, Class<?>> map = connection.getTypeMap();
            map.put("PEDIDO", Pedido.class);
            map.put("CLIENTE", Cliente.class);
            map.put("PRODUTO", Produto.class);
            map.put("PIZZA", Pizza.class);
            map.put("ENDERECO", Endereco.class);
            map.put("TELEFONE_CLIENTE", Telefone.class);
            map.put("ITEMPEDIDO", ItemPedido.class);
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = (Pedido) rs.getObject(1);
                pedidos.add(pedido);
            }
            rs.close();

            return pedidos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Oracle.close(stmt);
            Oracle.close(connection);
        }
    }
    
}
