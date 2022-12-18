package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

            String userEmail = "Enter like: " + cUser.getEmail();
            tvUserEmail.setText(userEmail);


            Toast.makeText(this, "User not null", Toast.LENGTH_LONG).show();

        } else {
            showNotSigned();
            Toast.makeText(this, "User null", Toast.LENGTH_LONG).show();
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
                    idPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showSigned();
                        Toast.makeText(getApplicationContext(), "User SignUp Successful", Toast.LENGTH_LONG).show();
                    } else {
                        showNotSigned();
                        Toast.makeText(getApplicationContext(), "User SignUp Fail", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Email and Password", Toast.LENGTH_LONG).show();

        }
    }

    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(idLogin.getText().toString()) &&
                !TextUtils.isEmpty(idPassword.getText().toString())) {
            myAuth.signInWithEmailAndPassword(idLogin.getText().toString(), idPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                showSigned();
                                Toast.makeText(getApplicationContext(), "User SignIn Successful", Toast.LENGTH_LONG).show();
                            } else {
                                showNotSigned();
                                Toast.makeText(getApplicationContext(), "User SignIn Fail", Toast.LENGTH_LONG).show();
                            }
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
        System.out.println(myAuth.getCurrentUser().getEmail());
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
