package com.example.myapplication2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;

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
        Intent intent = getIntent();
        if(intent!=null){
            tvName.setText(intent.getStringExtra("user_name"));
            tvLastName.setText(intent.getStringExtra("user_last_name"));
            tvEmail.setText(intent.getStringExtra("user_email"));
        }
    }
}
