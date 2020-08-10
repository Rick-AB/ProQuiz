package com.ricknotes.proquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.ricknotes.proquiz.Model.Constants;
import com.ricknotes.proquiz.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class TotalScoreActivity extends AppCompatActivity {
    private CircleImageView mProfileImage;
    private TextView mTotalQuestions, mCorrectAnswer, mPercentage;
    private Button mTryAgain, mNewQuiz;
    private String mCategory;
    private String mDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_score);

        initViews();

        SharedPreferences preferences = getSharedPreferences(Constants.MY__PREFS, MODE_PRIVATE);
        String imageUrl = preferences.getString(Constants.IMAGE_URL,"");

        if (!imageUrl.equals("")){
            Glide.with(this)
                    .load(imageUrl)
                    .into(mProfileImage);
        }else {
            mProfileImage.setImageDrawable(getDrawable(R.drawable.profileimage));
        }


        Intent intent = getIntent();
        mCategory = intent.getStringExtra(Constants.QUESTION_CATEGORY);
        mDifficulty = intent.getStringExtra(Constants.QUESTION_DIFFICULTY);
        int totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0);
        int score = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0);

        if (totalQuestions != 0){
            double percentage = Math.round(((double) score/ (double) totalQuestions) * 100);

            String percentMessage = percentage + "% Score";
            String questionsAnswered = totalQuestions + " questions";
            String correctAnswers = score + " is correct";

            mPercentage.setText(percentMessage);
            mTotalQuestions.setText(questionsAnswered);
            mCorrectAnswer.setText(correctAnswers);

        }
    }

    private void initViews() {
        mProfileImage = findViewById(R.id.total_score_profile_image);
        mCorrectAnswer = findViewById(R.id.total_score_correct_ans);
        mTotalQuestions = findViewById(R.id.total_score_total_questions);
        mPercentage = findViewById(R.id.total_score_percentage_TV);
        mNewQuiz = findViewById(R.id.total_score_take_new_quiz_btn);
        mTryAgain = findViewById(R.id.total_score_try_again_btn);

    }

    public void totalScoreClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.total_score_try_again_btn){
            intent = new Intent(this, QuestionsActivity.class);
            intent.putExtra(Constants.QUESTION_CATEGORY, mCategory);
            intent.putExtra(Constants.QUESTION_DIFFICULTY, mDifficulty);
        }else if (view.getId() == R.id.total_score_take_new_quiz_btn){
            intent = new Intent(this, CategoriesActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        Animatoo.animateShrink(this);
        finish();
    }
}