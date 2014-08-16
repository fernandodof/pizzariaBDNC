/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.banco.Oracle;
import br.edu.ifpb.bdnc.beans.Pizza;
import br.edu.ifpb.bdnc.beans.Produto;
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
public class PizzaDAO {
    public boolean persistit(Pizza p) throws SQLException {
        boolean resultado = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "insert into produtos values(?)";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, p);
            pstmt.executeQuery();
            resultado = true;
        }catch(SQLException e){
            e.printStackTrace();
            resultado = false;
        }finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
        
        return resultado;
    }

    public boolean atualizar(Pizza p) throws SQLException {
        boolean resultado = false;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "update produtos p set value(p) = ? where p.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, p);
            pstmt.setObject(2, p.getCodigo());
            pstmt.executeQuery();
            resultado = true;
        } catch(SQLException e ){
            e.printStackTrace();
            resultado = false;
        }finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
        return resultado;
    }

    public boolean excluir(Pizza p) throws SQLException {
        boolean resultado;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "delete produtos p where p.codigo = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, p.getCodigo());
            pstmt.executeQuery();
            resultado = true;
        } catch(SQLException e){
            e.printStackTrace();
            resultado = false;
        }finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
        return resultado;
    }

    public List<Pizza> listarTodos() throws SQLException {
        List<Pizza> pizzas = new ArrayList<>();

        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select value(p) from produtos p where value(p) is of (only pizza) order by p.nome";
        try {
            connection = Oracle.getConnection();
            Map<String, Class<?>> map = connection.getTypeMap();
            map.put("PRODUTO", Produto.class);
            map.put("PIZZA", Pizza.class);

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pizza pizza = (Pizza) rs.getObject(1);
                pizzas.add(pizza);
            }
            rs.close();

            return pizzas;
        } finally {
            Oracle.close(stmt);
            Oracle.close(connection);
        }
    }
}
