package com.dba.iplugform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dba.iplugform.Data.DBHelper;

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