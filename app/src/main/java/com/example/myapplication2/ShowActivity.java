package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    private TextView tvName, tvLastName, tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init() {
        tvName = findViewById(R.id.textName);
        tvLastName = findViewById(R.id.textLastName);
        tvEmail = findViewById(R.id.textEmail);
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if(i!=null){
            tvName.setText(i.getStringExtra("user_name"));
            tvLastName.setText(i.getStringExtra("user_last_name"));
            tvEmail.setText(i.getStringExtra("user_email"));
        }
    }
}
