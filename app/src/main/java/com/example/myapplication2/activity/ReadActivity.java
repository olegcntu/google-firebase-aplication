package com.example.myapplication2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.attributes.Attributes;
import com.example.myapplication2.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<User> listTemp;

    private DatabaseReference myDB;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.read_layout);
        init();
        setOnClickItem();
    }

    private void init() {
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        myDB = FirebaseDatabase.getInstance().getReference(Attributes.USER_KEY);
        getDataFromDB();

    }

    private void getDataFromDB() {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listData.size() > 0) {
                    listData.clear();
                }
                if (listTemp.size() > 0) {
                    listTemp.clear();
                }
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user.name);
                    listTemp.add(user);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myDB.addValueEventListener(valueEventListener);
    }

    private void setOnClickItem() {
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            User user = listTemp.get(position);
            Intent intent = new Intent(ReadActivity.this, ShowActivity.class);
            intent.putExtra("user_name", user.name);
            intent.putExtra("user_last_name", user.secondName);
            intent.putExtra("user_email", user.email);
            startActivity(intent);
        });
    }
}
