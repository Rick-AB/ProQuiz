package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ricknotes.proquiz.Model.Constants;
import com.ricknotes.proquiz.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    private Button mQuizzes, mSettings, mDifficulty, mLogOut;
    private CircleImageView mProfile;
    private TextView mUserName;
    public static String SOURCE = "homeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        updateViews();
    }

    private void updateViews() {
        SharedPreferences preferences = getSharedPreferences(Constants.MY__PREFS, MODE_PRIVATE);

        String userName = preferences.getString(Constants.USER_NAME, "");
        String imageUrl = preferences.getString(Constants.IMAGE_URL, "");

        if (userName.equals("")){
            mUserName.setText(getString(R.string.string_user_name));
        }else {
            mUserName.setText(userName);
        }

        if (imageUrl.equals("")){
            mProfile.setImageResource(R.drawable.profileimage);
        }else {
            Glide.with(this)
                    .load(imageUrl)
                    .into(mProfile);
        }

    }

    private void initViews() {
        mUserName = findViewById(R.id.home_activity_user_name);
        mProfile = findViewById(R.id.home_activity_profile_img);
        mQuizzes = findViewById(R.id.home_activity_quizzes_btn);
        mSettings = findViewById(R.id.home_activity_settings_btn);
        mDifficulty = findViewById(R.id.home_activity_difficulty_btn);
        mLogOut = findViewById(R.id.home_activity_log_out_btn);
    }

    public void homeClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.home_activity_quizzes_btn:
                startQuiz();
                break;
            case R.id.home_activity_settings_btn:
                settings();
                break;
            case R.id.home_activity_difficulty_btn:
                difficulty();
                break;
            case R.id.home_activity_log_out_btn:
                logout();
                break;
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        finish();
        Animatoo.animateShrink(this);
        System.exit(0);
    }

    private void difficulty() {
        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(SOURCE,"homeActivity");
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

    private void settings() {
        Intent intent = new Intent(this, ProfilePageActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

    private void startQuiz() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViews();
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}