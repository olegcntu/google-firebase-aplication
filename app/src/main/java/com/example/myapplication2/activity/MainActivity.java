package com.example.myapplication2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication2.R;
import com.example.myapplication2.attributes.Attributes;
import com.example.myapplication2.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText idName, idLastName, idEmail;
    private DatabaseReference myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void onClickSave(View view) {
        String id = myDB.getKey();
        String name = idName.getText().toString();
        String lastName = idLastName.getText().toString();
        String email = idEmail.getText().toString();
        User newUser = new User(id, name, lastName, email);

        myDB.push().setValue(newUser);
    }

    public void onClickRead(View view) {
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(intent);
    }

    private void init() {
        idName = findViewById(R.id.editName);
        idLastName = findViewById(R.id.editLastName);
        idEmail = findViewById(R.id.editEmail);
        myDB = FirebaseDatabase.getInstance().getReference(Attributes.USER_KEY);
    }
}