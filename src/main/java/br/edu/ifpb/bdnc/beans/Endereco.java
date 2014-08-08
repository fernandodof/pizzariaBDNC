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
public class Endereco implements SQLData{
    private String rua, bairro, numero, cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return "ENDERECO";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.setRua(stream.readString());
        this.setBairro(stream.readString());
        this.setNumero(stream.readString());
        this.setCep(stream.readString());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(this.getRua());
        stream.writeString(this.getBairro());
        stream.writeString(this.getNumero());
        stream.writeString(this.getCep());
    }
}
