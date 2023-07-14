package com.dba.iplugform.domain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dba.iplugform.R;

public class InventarioGestion extends AppCompatActivity {
    EditText txtId = null;
    EditText txtNombre = null;
    EditText txtDescEmail = null;
    EditText txtProvedor = null;
    EditText txtTipo = null;
    TableLayout tbProducto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_gestion);
        tbProducto = findViewById(R.id.tblRow);
        ScrollView scrollView = findViewById(R.id.scrollViewEntradas);
        /*
        if(tbProducto!=null)
        {
            tbProducto.removeAllViews();
            for(int i=0;i<15;i++) {
                View registro = LayoutInflater.from(this).inflate(R.layout.table_row, null, false);
                TextView colId = registro.findViewById(R.id.colIdio);
                TextView colNombre = registro.findViewById(R.id.colNombre);
                TextView colDesc = registro.findViewById(R.id.colDesc);
                TextView colProvedor = registro.findViewById(R.id.colProv);
                TextView colTipo = registro.findViewById(R.id.colTipo);
                colId.setText("Id " + i);
                colNombre.setText("Nombre " + i);
                colDesc.setText("Descripción " + i);
                colProvedor.setText("Provedor " + i);
                colTipo.setText("Tipo " + i);
                tbProducto.addView(registro);
            }
        }*/
        llenarTabla();
        tbProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(R.color.caja));
            }
        });
        //Implemento la funcion de busqueda propia del textbox de buscar
        EditText searchEditText = findViewById(R.id.searchTextBox);
        TextView textView1 = tbProducto.findViewById(R.id.colIdio);
        TextView textView2 = tbProducto.findViewById(R.id.colNombre);
        TextView textView3 = tbProducto.findViewById(R.id.colDesc);
        TextView textView4 = tbProducto.findViewById(R.id.colProv);
        TextView textView5 = tbProducto.findViewById(R.id.colTipo);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita implementación en este caso
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().toLowerCase();

                // Itera sobre cada fila y verifica si alguna contiene la subcadena buscada
                for (int i = 0; i < tbProducto.getChildCount(); i++) {
                    View view = tbProducto.getChildAt(i);
                    if (view instanceof TableRow) {
                        TableRow row = (TableRow) view;

                        // Verifica si alguno de los TextViews contiene la subcadena buscada
                        if (containsSubstring(row, searchText)) {
                            row.setVisibility(View.VISIBLE);
                        } else {
                            row.setVisibility(View.GONE);
                        }
                    }
                }
            }

            private boolean containsSubstring(TableRow row, String searchText) {
                for (int i = 0; i < row.getChildCount(); i++) {
                    View view = row.getChildAt(i);
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        String text = textView.getText().toString().toLowerCase();

                        if (text.contains(searchText)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se necesita implementación en este caso
            }
        });

    }

    public void llenarTabla() {
        SQLiteDatabase baseDatos = this.openOrCreateDatabase("boxs", Context.MODE_PRIVATE, null);
        Cursor fila = baseDatos.rawQuery("SELECT P.id_producto, P.nombre_producto, P.cantidad_producto, P.id_proveedor, P.id_categoria_producto FROM producto P", null);
        fila.moveToFirst();

        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.table_row, null, false);
            TextView CodigoProd = registro.findViewById(R.id.colIdio);
            TextView prodName = registro.findViewById(R.id.colNombre);
            TextView prodCant = registro.findViewById(R.id.colDesc);
            TextView idProv = registro.findViewById(R.id.colProv);
            TextView idTipo = registro.findViewById(R.id.colTipo);

            CodigoProd.setText(fila.getString(0));
            prodName.setText(fila.getString(1));
            prodCant.setText(fila.getString(2));
            idTipo.setText(fila.getString(3));
            idProv.setText(fila.getString(4));
            tbProducto.addView(registro);
        } while (fila.moveToNext());

        fila.close();
        baseDatos.close();
    }
}