package com.dba.iplugform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class iplugWEBvisor extends AppCompatActivity {

    WebView webview;
    EditText path;
    Button urlButton;
    String url = "www.google.com.ec";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iplug_webvisor);
        webview = (WebView) findViewById(R.id.visoriPlugandForm);
        //setContentView(webview);
        webview.setWebViewClient(new WebViewClient());

        urlButton = findViewById(R.id.buttonURL);
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = (EditText) findViewById(R.id.textBoxURL);
                url= path.getText().toString();
                webview.loadUrl("http://"+url);
            }
        });
        webview.loadUrl("http://"+url);

    }
}