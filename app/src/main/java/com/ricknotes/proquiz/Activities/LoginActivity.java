package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ricknotes.proquiz.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button mLogin;
    private TextView mSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

    }

    private void initViews() {
        mEmail = findViewById(R.id.log_in_email);
        mPassword = findViewById(R.id.log_in_password);
        mLogin = findViewById(R.id.log_in_login_button);
        mSignUp = findViewById(R.id.log_in_sign_up_TV);
    }

    public void login(View view) {

    }

    public void signUp(View view) {

    }
}