/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.beans;

import br.edu.ifpb.bdnc.banco.DBUtils;
import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.setCodigo(stream.readInt());
        this.setNome(stream.readString());
        this.setPreco(stream.readFloat());
        this.setIngredientes(stream.readString());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(this.getCodigo());
        stream.writeString(this.getNome());
        stream.writeFloat(this.getPreco());
        stream.writeString(this.getIngredientes());
    }
}
