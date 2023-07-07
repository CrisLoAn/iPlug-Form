package com.dba.iplugform;

import static com.dba.iplugform.R.id.textView2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dba.iplugform.Data.DBHelper;
import com.dba.iplugform.domain.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button longin;
    String Title = "Login";
    private Cursor row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(Title);   //Establecemos el nombre del formulario
        //Recupearmos el nombre del usuario y contraseña
        user = (EditText) findViewById(R.id.userNameTextBox);
        password = (EditText) findViewById(R.id.passwordTextBox);

        longin = findViewById(R.id.logInButton);
        longin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session(v);
            }
        });

        TextView colorConexion = findViewById(R.id.textView2);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isConnected = false; // Variable para seguir el estado de la conectividad
                while (true) {
                    // Verificar la conectividad y establecer el color en consecuencia
                    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo[] networkInfoArray = cm.getAllNetworkInfo();
                    for (NetworkInfo networkInfo : networkInfoArray) {
                        if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                            isConnected = true;
                            break;
                        } else if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                            isConnected = true;
                            break;
                        } else {
                            isConnected = false;
                        }
                    }

                    // Establecer el color de acuerdo al estado de la conectividad
                    boolean finalIsConnected = isConnected;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalIsConnected) {
                                colorConexion.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                            } else {
                                colorConexion.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                            }
                        }
                    });

                    try {
                        Thread.sleep(1000); // Esperar 1 segundo antes de volver a verificar la conectividad
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

    }

    public void session(View v)
    {
        DBHelper admin = new DBHelper(this, "dipfb",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String USERNAME = user.getText().toString();
        String USERPASSWORD = password.getText().toString();
        row = db.rawQuery("SELECT user_name, user_key FROM user WHERE USER_NAME = '"+USERNAME+"' AND user_key='"+USERPASSWORD+"' AND state = 'A';",null);
        try
        {
            if(row.moveToFirst())
            {
                if(USERNAME.equals(row.getString(0)) && USERPASSWORD.equals(row.getString(1)))
                {
                    Toast msg = Toast.makeText(this,"Granted!",Toast.LENGTH_LONG);
                    msg.show();
                    Intent intent = new Intent(MainActivity.this, iplugWEBvisor.class);
                    startActivity(intent);
                }
            }
            else
            {
                Toast msg = Toast.makeText(this,"Not found!",Toast.LENGTH_LONG);
                msg.show();
            }
        }catch (Exception e)
        {
            Toast msg = Toast.makeText(this,"Something wrong happens!",Toast.LENGTH_LONG);
            msg.show();
        }
        db.close();
    }
}