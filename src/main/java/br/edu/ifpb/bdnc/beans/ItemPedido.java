/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.beans;

import br.edu.ifpb.bdnc.banco.DBUtils;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ItemPedido implements SQLData {

    private int Codigo;
    private Produto prod;
    private int quantidade;
    private double preco;

    public ItemPedido() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public Produto getProd() {
        return prod;
    }

    public void setProd(Produto prod) {
        this.prod = prod;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return this.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.setCodigo(stream.readInt());
        try {
            this.setProd((Produto) DBUtils.getRef(stream));
        } catch (Exception ex) {
            Logger.getLogger(ItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setQuantidade(stream.readInt());
        this.setPreco(stream.readDouble());
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(this.getCodigo());
        try {
            DBUtils.setupRef(stream, prod, "PRODUTOS", "codigo");
        } catch (Exception ex) {
            Logger.getLogger(ItemPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        stream.writeInt(this.getQuantidade());
        stream.writeDouble(this.getPreco());
    }

}
