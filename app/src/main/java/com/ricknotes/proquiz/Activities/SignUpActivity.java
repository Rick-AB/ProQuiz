package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ricknotes.proquiz.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText mFullName, mEmail, mPassword, mConfirmPassword;
    private Button mCreateAccountBtn;
    private CheckBox mAgreement;
    private TextView mTerms, mLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activivty);

        initView();
    }

    private void initView() {
        mFullName = findViewById(R.id.sign_up_person_name_edit_text);
        mEmail = findViewById(R.id.sign_up_email_edit_text);
        mPassword = findViewById(R.id.sign_up_password_edit_text);
        mConfirmPassword = findViewById(R.id.sign_up_confirm_password);
        mCreateAccountBtn = findViewById(R.id.sign_up_sign_up_button);
        mAgreement = findViewById(R.id.sign_up_checkBox);
        mTerms = findViewById(R.id.sign_up_terms_TV);
        mLogin = findViewById(R.id.sign_up_login_TV);
    }

    public void createAccount(View view) {

    }
}