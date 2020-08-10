package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ricknotes.proquiz.R;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        int DELAY_LENGTH = 2000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            SplashScreen.this.startActivity(intent);
            SplashScreen.this.finish();
        }, DELAY_LENGTH);
    }
}