package com.dba.iplugform.domain;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dba.iplugform.R;

public class iplugWEBvisor extends AppCompatActivity {

    WebView webview;
    EditText path;
    Button urlButton;


    private ImageView evtGoToProductManager, IOManager, Register, exitOperation;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iplug_webvisor);
        Register = findViewById(R.id.RegisterIMG);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(iplugWEBvisor.this, ProductRegister.class);
                startActivity(intentReg);
            }
        });
        evtGoToProductManager = findViewById(R.id.imageView3);
        evtGoToProductManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSel = new Intent(iplugWEBvisor.this, InventarioGestion.class);
                startActivity(intentSel);
            }
        });
        IOManager = findViewById(R.id.IOLink);
        IOManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentIO = new Intent(iplugWEBvisor.this, InOuts.class);
                startActivity(intentIO);
            }
        });
        exitOperation = findViewById(R.id.exitIMG);
        exitOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }



}