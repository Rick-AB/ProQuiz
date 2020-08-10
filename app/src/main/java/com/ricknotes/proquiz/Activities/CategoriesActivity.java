package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.ricknotes.proquiz.Api.Api;
import com.ricknotes.proquiz.Api.ApiClient;
import com.ricknotes.proquiz.Model.Constants;
import com.ricknotes.proquiz.R;

public class CategoriesActivity extends AppCompatActivity {
    private Button mGeneral, mFilm, mMusic, mScienceNature, mComputer, mMaths, mSports, mGeo,
            mPolitics, mArts;
    private ImageButton mBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        initViews();

    }
    private void initViews(){
        mGeneral = findViewById(R.id.categories_general_knowledge_btn);
        mFilm = findViewById(R.id.categories_entertainment_film_btn);
        mMusic = findViewById(R.id.categories_entertainment_music_btn);
        mScienceNature = findViewById(R.id.categories_science_nature_btn);
        mComputer = findViewById(R.id.categories_science_computer_btn);
        mMaths = findViewById(R.id.categories_science_maths_btn);
        mSports = findViewById(R.id.categories_sports_btn);
        mGeo = findViewById(R.id.categories_geography_btn);
        mPolitics = findViewById(R.id.categories_politics_btn);
        mArts = findViewById(R.id.categories_arts_btn);
        mBackBtn = findViewById(R.id.categories_back_btn);
    }

    public void categoryClick(View view) {
        String tag = (String) view.getTag();

        Intent intent = new Intent(this, DifficultyActivity.class);
        intent.putExtra(Constants.QUESTION_CATEGORY,tag);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

    public void backClick(View view) {
        Animatoo.animateShrink(this);
        finish();
    }
}