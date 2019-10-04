package com.example.rusmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class PopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        Toolbar toolbar = findViewById(R.id.toolbarpopup);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Catatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
