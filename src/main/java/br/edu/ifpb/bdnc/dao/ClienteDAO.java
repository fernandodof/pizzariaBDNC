/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.banco.Oracle;
import br.edu.ifpb.bdnc.beans.Cliente;
import br.edu.ifpb.bdnc.beans.Endereco;
import br.edu.ifpb.bdnc.beans.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author magdiel-bruno
 */
public class ClienteDAO {

    public void persistir(Cliente c) throws SQLException {

        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "insert into clientes values(?)";

        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, c);
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public void atualizar(Cliente c) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "update clientes c set value(c) = ? where c.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, c);
            pstmt.setObject(2, c.getCodigo());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public void excluir(int codigo) throws SQLException{
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "delete clientes c where c.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public Cliente getClientePorCodigo(int codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = Oracle.getConnection();
            Map<String, Class<?>> map = connection.getTypeMap();
            map.put("CLIENTE", Cliente.class);
            map.put("ENDERECO", Endereco.class);
            map.put("TELEFONE_CLIENTE", Telefone.class);
            String sql = "SELECT value(c) FROM clientes c WHERE c.codigo = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
            Cliente cliente = null;
            while (rs.next()) {
                cliente = (Cliente) rs.getObject(1);
            }
            return cliente;
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select value(c) from clientes c order by c.nome";
        try {
            connection = Oracle.getConnection();
            Map<String, Class<?>> map = connection.getTypeMap();
            map.put("CLIENTE", Cliente.class);
            map.put("ENDERECO", Endereco.class);
            map.put("TELEFONE_CLIENTE", Telefone.class);

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = (Cliente) rs.getObject(1);
                clientes.add(cliente);
            }
            rs.close();

            return clientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Oracle.close(stmt);
            Oracle.close(connection);
        }
    }
}
