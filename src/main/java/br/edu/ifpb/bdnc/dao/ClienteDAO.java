/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.banco.Oracle;
import br.edu.ifpb.bdnc.beans.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }

    public void atualizar(int codigo) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "update clients c set value(c) = ? where c.cod = ?";
        try {
            connection = Oracle.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, c);
            pstmt.setObject(2, c.getName);
            pstmt.executeQuery();
        } finally {
            Oracle.close(pstmt);
            Oracle.close(connection);
        }
    }
//
//    public void excluir(Client c) throws SQLException {
//        Connection connection = null;
//        PreparedStatement pstmt = null;
//        String sql = "delete clients c where c.name = ?";
//        try {
//            connection = Oracle.getConnection();
//            pstmt = connection.prepareStatement(sql);
//            pstmt.setObject(1, m.getTitulo());
//            pstmt.executeQuery();
//        } finally {
//            Oracle.close(pstmt);
//            Oracle.close(connection);
//        }
//    }
//
//    public List<Movie> listarTodos() throws SQLException {
//        List<Movie> movies = new ArrayList<>();
//
//        Connection connection = null;
//        PreparedStatement stmt = null;
//        String sql = "select value(f) from filmes f order by f.titulo";
//        try {
//            connection = Oracle.getConnection();
//            Map<String, Class<?>> map = connection.getTypeMap();
//            map.put("FILME", Movie.class);
//            map.put("DIRETOR", Director.class);
//            map.put("TELEFONE", Phone.class);
//
//            stmt = connection.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Movie movie = (Movie) rs.getObject(1);
//                movies.add(movie);
//            }
//            rs.close();
//
//            return movies;
//        } finally {
//            Oracle.close(stmt);
//            Oracle.close(connection);
//        }
//    }
    
    public long findFinalCod(){
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "select cod from clients order";
        try {
            connection = Oracle.getConnection();
            Map<String, Class<?>> map = connection.getTypeMap();
            map.put("FILME", Movie.class);
            map.put("DIRETOR", Director.class);
            map.put("TELEFONE", Phone.class);

            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = (Movie) rs.getObject(1);
                movies.add(movie);
            }
            rs.close();

            return movies;
        } finally {
            Oracle.close(stmt);
            Oracle.close(connection);
        }
    }
}
