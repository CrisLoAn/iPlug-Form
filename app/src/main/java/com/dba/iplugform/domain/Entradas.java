package com.dba.iplugform.domain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dba.iplugform.R;
public class Entradas extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_entradas, container, false);

        TableLayout tableLayout = view2.findViewById(R.id.tbLayoutEntradas);
        if (tableLayout != null) {
            tableLayout.removeAllViews();
            for (int i = 0; i < 15; i++) {
                View registro = getLayoutInflater().inflate(R.layout.table_row_entradas, null, false);
                TextView colId = registro.findViewById(R.id.COLio);
                TextView colNombre = registro.findViewById(R.id.colDateIO);
                TextView colDesc = registro.findViewById(R.id.colUnits);
                TextView colProvedor = registro.findViewById(R.id.colMotivo);
                colId.setText("Id " + i);
                colNombre.setText("Nombre " + i);
                colDesc.setText("Descripción " + i);
                colProvedor.setText("Provedor " + i);
                tableLayout.addView(registro);
            }
        }
        return view2;
    }
}