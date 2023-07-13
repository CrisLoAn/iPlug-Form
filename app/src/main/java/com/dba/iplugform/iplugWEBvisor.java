package com.dba.iplugform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class iplugWEBvisor extends AppCompatActivity {

    WebView webview;
    EditText path;
    Button urlButton;
    String url = "www.google.com.ec";

    private ImageView evtGoToProductManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iplug_webvisor);
        evtGoToProductManager = findViewById(R.id.imageView3);
        evtGoToProductManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(iplugWEBvisor.this, InventarioGestion.class);
                startActivity(intent);
            }
        });

    }



}