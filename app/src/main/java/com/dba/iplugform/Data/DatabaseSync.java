package com.dba.iplugform.Data;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSync {
    private static final String REMOTE_DB_URL = "jdbc:mysql://127.0.0.1:1250/IplugAndForm";
    private static final String REMOTE_DB_USERNAME = "root";
    private static final String REMOTE_DB_PASSWORD = "omegaW";

    // Datos de la base de datos local
    private static final String LOCAL_DB_PATH = "/data/data/com.dba.iplugform/databases/boxs.sqlite";
    private Context context;

    public DatabaseSync(Context context) {
        this.context = context;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(REMOTE_DB_URL, REMOTE_DB_USERNAME, REMOTE_DB_PASSWORD);
    }

    public void syncData() {
        Connection remoteConnection = null;
        Connection localConnection = null;

        try {
            // Establecer la conexión con la base de datos en línea
            Class.forName(driver).newInstance ();

            remoteConnection = DriverManager.getConnection(REMOTE_DB_URL, REMOTE_DB_USERNAME, REMOTE_DB_PASSWORD);

            // Establecer la conexión con la base de datos local
            localConnection = DriverManager.getConnection("jdbc:sqlite:" + LOCAL_DB_PATH);

            // Sincronizar la tabla "user"
            syncTable("user", localConnection, remoteConnection);

            // Sincronizar la tabla "Movimiento_inventario"
            syncTable("Movimiento_inventario", localConnection, remoteConnection);

            // Sincronizar la tabla "categoria_producto"
            syncTable("categoria_producto", localConnection, remoteConnection);

            // Sincronizar la tabla "Proveedor"
            syncTable("Proveedor", localConnection, remoteConnection);

            // Sincronizar la tabla "Producto"
            syncTable("Producto", localConnection, remoteConnection);

            // Sincronizar la tabla "Producto_Movimiento_Inventario"
            syncTable("Producto_Movimiento_Inventario", localConnection, remoteConnection);

            Toast.makeText(context, "Synchronization completed successfully.", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar las conexiones
            try {
                if (localConnection != null)
                    localConnection.close();
                if (remoteConnection != null)
                    remoteConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void syncTable(String tableName, Connection localConnection, Connection remoteConnection) throws SQLException {
        // Obtener los datos de la tabla desde la base de datos local
        Statement localStatement = localConnection.createStatement();
        ResultSet localResult = localStatement.executeQuery("SELECT * FROM " + tableName);

        // Obtener los datos actuales de la tabla en línea
        Statement remoteSelectStatement = remoteConnection.createStatement();
        ResultSet remoteResult = remoteSelectStatement.executeQuery("SELECT * FROM " + tableName);

        // Crear una lista para almacenar las claves primarias de los registros existentes en la base de datos en línea
        List<String> existingPrimaryKeys = new ArrayList<>();

        // Agregar las claves primarias de los registros existentes a la lista
        while (remoteResult.next()) {
            String primaryKeyValue = remoteResult.getString("<primary_key_column_name>");
            existingPrimaryKeys.add(primaryKeyValue);
        }

        // Insertar solo los nuevos registros en la tabla de la base de datos en línea
        PreparedStatement remoteInsertStatement = remoteConnection.prepareStatement(getInsertQuery(tableName));
        while (localResult.next()) {
            String primaryKeyValue = localResult.getString("<primary_key_column_name>");

            // Verificar si la clave primaria ya existe en la base de datos en línea
            if (!existingPrimaryKeys.contains(primaryKeyValue)) {
                for (int i = 1; i <= localResult.getMetaData().getColumnCount(); i++) {
                    remoteInsertStatement.setObject(i, localResult.getObject(i));
                }
                remoteInsertStatement.executeUpdate();
            }
        }
    }



    private static String getInsertQuery(String tableName) {
        // Crear la consulta de inserción en base al nombre de la tabla y los campos correspondientes
        // Aquí se asume que los nombres de columna son los mismos tanto en la tabla local como en la tabla en línea
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        for (int i = 0; i < getNumColumns(tableName); i++) {
            query.append("?");
            if (i < getNumColumns(tableName) - 1) {
                query.append(",");
            }
        }
        query.append(")");
        return query.toString();
    }

    private static int getNumColumns(String tableName) {
        // Obtener el número de columnas de la tabla
        // Esto se basa en el modelo de datos proporcionado y asume que las tablas tienen la misma estructura
        switch (tableName) {
            case "user":
                return 4;
            case "Movimiento_inventario":
                return 2;
            case "categoria_producto":
                return 2;
            case "Proveedor":
                return 4;
            case "Producto":
                return 8;
            case "Producto_Movimiento_Inventario":
                return 7;
            default:
                return 0;
        }
    }
}