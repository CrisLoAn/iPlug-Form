package com.dba.iplugform.domain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dba.iplugform.R;

public class Salidas extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_salidas, container, false);

        TableLayout tableLayout = vista.findViewById(R.id.tbSalidas);
        if (tableLayout != null) {
            tableLayout.removeAllViews();
            for (int i = 0; i < 45; i++) {
                View registro = inflater.inflate(R.layout.table_row_salidas, null, false);
                TextView colpro = registro.findViewById(R.id.idProductoText);
                TextView colcli = registro.findViewById(R.id.idClienteText);
                TextView colcanti = registro.findViewById(R.id.cantidadText);
                TextView colfecha = registro.findViewById(R.id.fechaText);
                TextView colDesc = registro.findViewById(R.id.fechaText);
                colpro.setText(String.valueOf(i));
                colcli.setText(String.valueOf(i));
                colcanti.setText(String.valueOf(i + 154));
                colfecha.setText("01/01/2000");
                colDesc.setText("compra");
                tableLayout.addView(registro);
            }
        }
        return vista;
    }
}