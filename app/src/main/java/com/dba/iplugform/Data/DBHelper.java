package com.dba.iplugform.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "boxs.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE user(user_code INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,user_name TEXT, user_key TEXT, state TEXT);";
        db.execSQL(query);
        db.execSQL("INSERT INTO user (user_name, user_key, state) values('admin','admin global','A'), ('global','','A');");
        db.execSQL("CREATE TABLE Movimiento_inventario (" +
                "id_movimiento_inventario INTEGER PRIMARY KEY," +
                "descripcion_movimiento_inventario TEXT NOT NULL)");

        db.execSQL("CREATE TABLE categoria_producto (" +
                "id_categoria_producto INTEGER PRIMARY KEY," +
                "nombre_categoria_producto TEXT NOT NULL)");

        db.execSQL("INSERT INTO categoria_producto (nombre_categoria_producto) VALUES\n" +
                "( 'Hogar y Accesorios'),\n" +
                "( 'Artículos para bebés y niños'),\n" +
                "( 'Carnes, Mariscos y Pescados'),\n" +
                "( 'Tecnológicos'),\n" +
                "( 'Limpieza')");

        db.execSQL("CREATE TABLE Proveedor (" +
                "id_proveedor INTEGER PRIMARY KEY," +
                "nombre_proveedor TEXT NOT NULL," +
                "direccion_proveedor TEXT NOT NULL," +
                "telefono_proveedor TEXT NOT NULL)");

        db.execSQL("INSERT INTO Proveedor (nombre_proveedor, direccion_proveedor, telefono_proveedor) VALUES\n" +
                "('Pronaca', 'Calle de la Industria 123, Quito, Ecuador', '+593-2-123-4567'),\n" +
                "('Electrolux', 'Avenida de la Tecnología 456, Guayaquil, Ecuador', '+593-4-234-5678'),\n" +
                "('Samsung', 'Calle Principal 789, Cuenca, Ecuador', '+593-7-345-6789'),\n" +
                "('Nestlé', 'Avenida del Sabor 987, Ambato, Ecuador', '+593-3-456-7890'),\n" +
                "('La Favorita', 'Calle de la Comida 654, Quito, Ecuador', '+593-2-567-8901')");

        db.execSQL("CREATE TABLE Producto (" +
                "id_producto INTEGER NOT NULL," +
                "id_categoria_producto INTEGER NOT NULL," +
                "id_proveedor INTEGER NOT NULL," +
                "cantidad_producto INTEGER NOT NULL," +
                "nombre_producto TEXT NOT NULL," +
                "precio_producto REAL DEFAULT 0.00 NOT NULL," +
                "codigo_barras_producto TEXT NOT NULL," +
                "locacion_producto TEXT NOT NULL," +
                "PRIMARY KEY (id_producto, id_categoria_producto, id_proveedor)," +
                "FOREIGN KEY (id_categoria_producto) REFERENCES categoria_producto (id_categoria_producto) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY (id_proveedor) REFERENCES Proveedor (id_proveedor) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        db.execSQL("INSERT INTO Producto (id_producto, id_categoria_producto, id_proveedor, cantidad_producto, nombre_producto, precio_producto, codigo_barras_producto, locacion_producto) VALUES\n" +
                "(1, 1, 1, 25, 'Lámpara de mesa', 19.99, '1234567890123', ' -0.180969, -78.467063'),\n" +
                "(2, 2, 2, 15, 'Silla para bebé', 39.99, '2345678901234', '-0.218607, -78.509881'),\n" +
                "(3, 3, 3, 35, 'Filete de salmón', 12.99, '3456789012345', '-0.204562, -78.493126'),\n" +
                "(4, 4, 4, 20, 'Televisor 4K', 699.99, '4567890123456', '-0.224554, -78.518248'),\n" +
                "(5, 5, 5, 55, 'Detergente líquido', 4.99, '5678901234567', '-0.218896, -78.516020'),\n" +
                "(6, 1, 1, 18, 'Cojín decorativo', 9.99, '6789012345678', '-0.211629, -78.508840'),\n" +
                "(7, 2, 2, 13, 'Juego de cubiertos para bebé', 7.99, '7890123456789', '-0.204966, -78.515008'),\n" +
                "(8, 3, 3, 42, 'Filete de tilapia', 8.99, '8901234567890', '-0.200408, -78.504296'),\n" +
                "(9, 4, 4, 28, 'Refrigeradora', 849.99, '9012345678901', '-0.219578, -78.504989'),\n" +
                "(10, 5, 5, 60, 'Limpiador multiusos', 2.99, '0123456789012', '-0.221853, -78.504005'),\n" +
                "(11, 1, 1, 22, 'Juego de toallas', 14.99, '9876543210987', '-0.204586, -78.487968'),\n" +
                "(12, 2, 2, 14, 'Bañera para bebé', 29.99, '8765432109876', '-0.226297, -78.512239'),\n" +
                "(13, 3, 3, 50, 'Filete de camarón', 10.99, '7654321098765', '-0.226126, -78.491064'),\n" +
                "(14, 4, 4, 16, 'Smartphone', 899.99, '6543210987654', '-0.232038, -78.525569'),\n" +
                "(15, 5, 5, 68, 'Detergente en polvo', 3.99, '5432109876543', '-0.216497, -78.518780'),\n" +
                "(16, 1, 1, 20, 'Manta de lana', 17.99, '4321098765432', '-0.212548, -78.505493'),\n" +
                "(17, 2, 2, 12, 'Biberón', 5.99, '3210987654321', '-0.195824, -78.495157'),\n" +
                "(18, 3, 3, 45, 'Filete de trucha', 9.99, '2109876543210', '-0.205784, -78.494088'),\n" +
                "(19, 4, 4, 17, 'Tablet', 329.99, '1098765432109', '-0.206868, -78.509522'),\n" +
                "(20, 5, 5, 38, 'Toallitas desinfectantes', 1.99, '0987654321098', '-0.214229, -78.510436')\n ");

        db.execSQL("CREATE TABLE Producto_Movimiento_Inventario (" +
                "id_PMI INTEGER NOT NULL," +
                "id_producto INTEGER NOT NULL," +
                "id_categoria_producto INTEGER NOT NULL," +
                "id_proveedor INTEGER NOT NULL," +
                "id_movimiento_inventario INTEGER NOT NULL," +
                "fecha TEXT NOT NULL," +
                "cantidad TEXT NOT NULL," +
                "PRIMARY KEY (id_PMI, id_producto, id_categoria_producto, id_proveedor, id_movimiento_inventario)," +
                "FOREIGN KEY (id_movimiento_inventario) REFERENCES Movimiento_inventario (id_movimiento_inventario) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY (id_producto, id_categoria_producto, id_proveedor) REFERENCES Producto (id_producto, id_categoria_producto, id_proveedor) ON DELETE NO ACTION ON UPDATE NO ACTION)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS Movimiento_inventario");
        db.execSQL("DROP TABLE IF EXISTS categoria_producto");
        db.execSQL("DROP TABLE IF EXISTS Proveedor");
        db.execSQL("DROP TABLE IF EXISTS Producto");
        db.execSQL("DROP TABLE IF EXISTS Producto_Movimiento_Inventario");
        onCreate(db);
    }
}
