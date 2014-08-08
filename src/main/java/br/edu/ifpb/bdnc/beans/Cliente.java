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
public class Cliente implements SQLData{
    private long codigo;
    private String nome;
    private Endereco endereco;
    private Telefone telefone;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return "CLIENTE";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.setCodigo(stream.readLong());
        this.setNome(stream.readString());
        this.setEndereco((Endereco)stream.readObject());
        this.setTelefone((Telefone) stream.readObject());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeLong(this.getCodigo());
        stream.writeString(this.getNome());
        stream.writeObject(this.getEndereco());
        stream.writeObject(this.getTelefone());
    }
}
