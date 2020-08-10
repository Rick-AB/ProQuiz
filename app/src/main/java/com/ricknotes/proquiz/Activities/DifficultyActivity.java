package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.ricknotes.proquiz.Model.Questions;
import com.ricknotes.proquiz.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.ricknotes.proquiz.Model.Constants.QUESTION_CATEGORY;
import static com.ricknotes.proquiz.Model.Constants.QUESTION_DIFFICULTY;

public class DifficultyActivity extends AppCompatActivity {
    private String mCategory;
    private List<String> mCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        initCategories();

        mCategory = getIntent().getStringExtra(QUESTION_CATEGORY);
        if (mCategory == null || mCategory.equals("")){
           mCategory = mCategories.get(new Random().nextInt(mCategories.size()));
        }
    }

    private void initCategories() {
        mCategories = new ArrayList<>(Arrays.asList("9","11","12","17","18","19","21"
                ,"22","24","25"));
    }

    public void difficultyClick(View view) {
        String tag = (String) view.getTag();

        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.putExtra(QUESTION_CATEGORY, mCategory);
        intent.putExtra(QUESTION_DIFFICULTY, tag);

        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    public void backClick(View view) {
        Animatoo.animateShrink(this);
        finish();
    }
}