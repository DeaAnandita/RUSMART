package com.example.rusmart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.rusmart.Model.ModelGuru;
import com.example.rusmart.Model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    Button btnlogin;
    private ProgressDialog progressBar;

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
                progressBar = new ProgressDialog(Login.this);

                progressBar.setMessage("Please wait");
                progressBar.show();
                progressBar.setCancelable(false);
                user.setUsername (txtusername.getText().toString());
                user.setPassword ( txtusername.getText().toString());
                AndroidNetworking.post("http://192.168.43.123/rusmart/login.php")
                        .addBodyParameter("username",txtusername.getText().toString())
                        .addBodyParameter("password",txtpassword.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("hasil", "onResponse: "+response);
                                try {
                                    JSONObject respon= response.getJSONObject("hasil");
                                    boolean sukses = respon.getBoolean("respon");
                                    if (sukses){
                                        //simpan share pref, ke main menu
                                        Toast.makeText(getApplicationContext(),"Login sukses", Toast.LENGTH_LONG).show();
                                        if (progressBar.isShowing()){
                                            progressBar.dismiss();
                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Login gagal", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    if (progressBar.isShowing()){
                                        progressBar.dismiss();
                                    }
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                if (progressBar.isShowing()){
                                    progressBar.dismiss();
                                }
                                System.out.println("lala4");
                                Log.d("errorku", "onError: "+anError.getErrorCode());
                                Log.d("errorku", "onError: "+anError.getErrorBody());
                                Log.d("errorku", "onError: "+anError.getErrorDetail());

                            }
                        });
            }
        });
    }
}
