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
public class Pizza extends Produto implements SQLData{
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return "PIZZA";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        Array array = stream.readArray();
        if (array != null) {
            Object[] result = (Object[]) array.getArray();
            for (Object obj : result) {
                ingredientes.add((Ingrediente) obj);
            }
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        try {
            DBUtils.setupArrays(stream, "INGREDIENTES", ingredientes);
        } catch (Exception ex) {
            Logger.getLogger(Pizza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
