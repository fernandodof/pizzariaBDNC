/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author magdiel-bruno
 */
public class Pizza extends Produto implements SQLData {

    private String ingredientes;

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return "PIZZA";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        super.readSQL(stream, typeName);
        setIngredientes(stream.readString());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        super.writeSQL(stream);
        stream.writeString(this.getIngredientes());
    }

//    @Override
//    public String toString() {
//        return "Pizza{" + "ingredientes=" + ingredientes + '}';
//    }   
}
