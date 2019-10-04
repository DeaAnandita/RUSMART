package com.example.rusmart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rusmart.Model.UserModel;

public class Login extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final UserModel user = new UserModel();
        txtusername = (EditText) findViewById(R.id.txtusername);
        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //command here

                user.setUsername (txtusername.getText().toString());
                user.setPassword ( txtusername.getText().toString());
                if (user.getUsername().equalsIgnoreCase("admin")
                        && user.getPassword().equalsIgnoreCase("admin")) {
                    Toast.makeText(getApplicationContext(), "Sukses Login", Toast.LENGTH_LONG).show();
                    Intent in=new Intent(Login.this,HomeDashboard.class);
                    startActivity(in);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal Login", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
