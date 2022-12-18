package com.example.myapplication2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;
import com.example.myapplication2.attributes.Attributes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText idLogin, idPassword;
    private FirebaseAuth myAuth;
    private Button bStart, RegistrationButton, EnterButton, SignOut;
    private TextView tvUserEmail;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = myAuth.getCurrentUser();
        if (cUser != null) {
            showSigned();

            String userEmail = Attributes.ENTER_MESSAGE + cUser.getEmail();
            tvUserEmail.setText(userEmail);


            Toast.makeText(this, Attributes.OK_USER, Toast.LENGTH_LONG).show();

        } else {
            showNotSigned();
            Toast.makeText(this, Attributes.NO_USER, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    private void init() {
        tvUserEmail = findViewById(R.id.tvUserEmail);
        bStart = findViewById(R.id.bStart);
        idLogin = findViewById(R.id.idLogin);
        idPassword = findViewById(R.id.idPassword);
        myAuth = FirebaseAuth.getInstance();
        EnterButton = findViewById(R.id.idEnterButton);
        RegistrationButton = findViewById(R.id.idRegistrationButton);
        SignOut = findViewById(R.id.bSignOut);
    }

    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(idLogin.getText().toString()) &&
                !TextUtils.isEmpty(idPassword.getText().toString())) {
            myAuth.createUserWithEmailAndPassword(idLogin.getText().toString(),
                    idPassword.getText().toString()).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            showSigned();
                            Toast.makeText(getApplicationContext(), Attributes.SECCESSFUL_SIGN_UP, Toast.LENGTH_LONG).show();
                        } else {
                            showNotSigned();
                            Toast.makeText(getApplicationContext(), Attributes.NO_SECCESSFUL_SIGN_UP, Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), Attributes.ENTER_EMAIL_AND_PASSWORD, Toast.LENGTH_LONG).show();

        }
    }

    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(idLogin.getText().toString()) &&
                !TextUtils.isEmpty(idPassword.getText().toString())) {
            myAuth.signInWithEmailAndPassword(idLogin.getText().toString(), idPassword.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            showSigned();
                            Toast.makeText(getApplicationContext(), Attributes.SECCESSFUL_SIGN_UP, Toast.LENGTH_LONG).show();
                        } else {
                            showNotSigned();
                            Toast.makeText(getApplicationContext(), Attributes.NO_SECCESSFUL_SIGN_UP, Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    public void onClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();

        showNotSigned();
    }

    public void onClickStart(View view) {
        myAuth.getCurrentUser();
        System.out.println(Objects.requireNonNull(myAuth.getCurrentUser()).getEmail());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void showSigned() {
        bStart.setVisibility(View.VISIBLE);
        tvUserEmail.setVisibility(View.VISIBLE);
        SignOut.setVisibility(View.VISIBLE);

        idLogin.setVisibility(View.GONE);
        idPassword.setVisibility(View.GONE);
        RegistrationButton.setVisibility(View.GONE);
        EnterButton.setVisibility(View.GONE);
    }

    private void showNotSigned() {
        bStart.setVisibility(View.GONE);
        tvUserEmail.setVisibility(View.GONE);
        SignOut.setVisibility(View.GONE);

        idLogin.setVisibility(View.VISIBLE);
        idPassword.setVisibility(View.VISIBLE);
        RegistrationButton.setVisibility(View.VISIBLE);
        EnterButton.setVisibility(View.VISIBLE);
    }
}
