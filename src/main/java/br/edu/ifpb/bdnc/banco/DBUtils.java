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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleResultSet;

public class DBUtils {

    public static void setupArrays(SQLOutput stream, String typeName,
            Collection<?> list) throws Exception {
        Field field = stream.getClass().getDeclaredField("conn");
        field.setAccessible(true);
        OracleConnection conn = (OracleConnection) field.get(stream);
        Array array = conn.createARRAY(typeName, list != null ? list.toArray()
                : null);
        stream.writeArray(array);
    }

    public static<T> void setupArrays(SQLOutput stream, String typeName,
            T[] list) throws Exception {
        Field field = stream.getClass().getDeclaredField("conn");
        field.setAccessible(true);
        OracleConnection conn = (OracleConnection) field.get(stream);
        Array array = conn.createARRAY(typeName, list);
        stream.writeArray(array);
    }

    public static void setupRef(SQLOutput stream, Object objeto,
            String nomeTabela, String attributeName) throws Exception {
        Field field = stream.getClass().getDeclaredField("conn");
        field.setAccessible(true);
        OracleConnection connection = (OracleConnection) field.get(stream);

        field = objeto.getClass().getDeclaredField(attributeName);
        field.setAccessible(true);
        Object value = field.get(objeto);
        PreparedStatement pstmt = null;
        try {
            // Sql para sele��o
            pstmt = connection.prepareStatement("SELECT REF(r) FROM "
                    + nomeTabela + " r " + "WHERE " + attributeName + " = ?");
            pstmt.setObject(1, value);
            OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
            if (rs.next()) {
                Ref ref = (Ref) rs.getObject(1);
                stream.writeRef(ref);
            }
        } finally {
            Oracle.close(pstmt);
        }
    }

    public static void setupRefs(SQLOutput stream, String typeName,
            Collection<?> list, String nomeTabela, String attributeName)
            throws Exception {
        Field field = stream.getClass().getDeclaredField("conn");
        field.setAccessible(true);
        OracleConnection connection = (OracleConnection) field.get(stream);
        // Lista de Refer�ncias
        List<Ref> refs = new ArrayList<Ref>();
        PreparedStatement pstmt = null;

        String parameter = createParameter(list, attributeName);
        try {
            if (!parameter.isEmpty()) {
                // Sql para seleção
                pstmt = connection.prepareStatement("SELECT REF(r) FROM "
                        + nomeTabela + " r " + "WHERE " + attributeName
                        + " in (" + parameter + ")");
                OracleResultSet rs = (OracleResultSet) pstmt.executeQuery();
                while (rs.next()) {
                    Ref ref = (Ref) rs.getObject(1);
                    refs.add(ref);
                }
            }

            setupArrays(stream, typeName, refs);
        } finally {
            Oracle.close(pstmt);
        }
    }

    private static String createParameter(Collection<?> list,
            String attributeName) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (Object objeto : list) {
            for (Method method : objeto.getClass().getMethods()) {
                if (method.getName().equalsIgnoreCase("get" + attributeName)) {
                    Object value = method.invoke(objeto);
                    sb.append(value);
                    sb.append(",");
                    break;
                }
            }
        }

        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : sb
                .toString();
    }
}
