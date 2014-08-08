/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.banco;

/**
 *
 * @author Fernando
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;

public class Oracle {

	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		OracleConnection connection = (OracleConnection) DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:XE", "bdnc", "bdnc");
		return connection;
	}

	public static void close(Connection connection) throws SQLException {
		if (connection != null && !connection.isClosed()){
			connection.close();
		}
	}

	public static void close(Statement pstmt) throws SQLException {
		if (pstmt != null){
			pstmt.close();
		}
	}

}
