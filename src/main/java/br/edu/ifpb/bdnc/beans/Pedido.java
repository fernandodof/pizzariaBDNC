package br.edu.ifpb.bdnc.beans;

import br.edu.ifpb.bdnc.banco.DBUtils;
import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */

public class Pedido implements SQLData{
    private int codigo;
    private Date data;
    private Cliente cli;
    private List itens = new ArrayList();

    public Pedido() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
        
    @Override
    public String getSQLTypeName() throws SQLException {
        return this.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.setCodigo(stream.readInt());
        this.setData(stream.readDate());
        this.setCli((Cliente) stream.readObject());
        Array array = stream.readArray();
        if(array != null){
            Object[] result = (Object[]) array.getArray();
            for(Object objeto: result){
                this.itens.add(objeto);
            }
        }
        
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(this.getCodigo());
        stream.writeDate((java.sql.Date) this.getData());
        try {
            DBUtils.setupRef(stream, cli, "CLIENTES", "CLI");
        } catch (Exception ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            DBUtils.setupArrays(stream, "ITENS", itens);
        } catch (Exception ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
