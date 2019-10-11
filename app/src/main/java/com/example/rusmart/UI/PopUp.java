package com.example.rusmart.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.rusmart.Model.ModelBarang;
<<<<<<< HEAD:app/src/main/java/com/example/rusmart/UI/PopUp.java
import com.example.rusmart.R;
=======
>>>>>>> 92c821487245f06c1b1f311ee768b6025588ea0f:app/src/main/java/com/example/rusmart/PopUp.java

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PopUp extends AppCompatActivity {
    Button btnnamabarang;
    Button btnsaveku;
    TextView txtnamabarang;
    private ProgressDialog progressBar;
    String foto="";
    EditText jumlah;
    ArrayList<ModelBarang> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        datalist=new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbarpopup);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnnamabarang=(Button)findViewById(R.id.btnnamabarang);
        jumlah=(EditText) findViewById(R.id.txtjumlah);
        btnsaveku=(Button)findViewById(R.id.btnsaveku);
        txtnamabarang=(TextView)findViewById(R.id.txtnamabarang);
        btnnamabarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foto="true";
                Intent i = new Intent(PopUp.this,QRBarcode.class);
                startActivityForResult(i, 1);
            }

        });

        btnsaveku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.putExtra("namaBarang",datalist.get(0).getNamabarang());
                intent.putExtra("kodeBarang",datalist.get(0).getId());
                intent.putExtra("jumlah",jumlah.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == PopUp.RESULT_OK) {
                System.out.println("lala1");
                String result = data.getStringExtra("text");

                progressBar = new ProgressDialog(PopUp.this);

                progressBar.setMessage("Please wait");
                progressBar.show();
                progressBar.setCancelable(false);
<<<<<<< HEAD:app/src/main/java/com/example/rusmart/UI/PopUp.java
                AndroidNetworking.post(baseURL.baseurl+"rusmart/getbarang.php")
=======
                AndroidNetworking.post("http://192.168.6.191/rusmart/getbarang.php")
>>>>>>> 92c821487245f06c1b1f311ee768b6025588ea0f:app/src/main/java/com/example/rusmart/PopUp.java
                        .addBodyParameter("kodebarang",result)
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    System.out.println("lala2");
                                    Log.d("hasil", "onResponse: "+response.toString());
                                    JSONArray result = response.getJSONArray("result");
                                    for (int i = 0; i <result.length() ; i++) {
                                        ModelBarang model = new ModelBarang();
                                        JSONObject json = result.getJSONObject(i);
                                        model.setId(json.getString("kodebarang"));
                                        model.setNamabarang(json.getString("namabarang"));
                                        datalist.add(model);
                                    }
                                    txtnamabarang.setText(datalist.get(0).getNamabarang());
                                    if (progressBar.isShowing()){
                                        progressBar.dismiss();
                                    }
                                } catch (JSONException e) {
                                    System.out.println("lala3");
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                System.out.println("lala4");
                                Log.d("errorku", "onError: "+anError.getErrorCode());
                                Log.d("errorku", "onError: "+anError.getErrorBody());
                                Log.d("errorku", "onError: "+anError.getErrorDetail());

                            }
                        });

            }
            if (resultCode == PopUp.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }//onActivityResult
}
