package com.ricknotes.proquiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ricknotes.proquiz.Api.Api;
import com.ricknotes.proquiz.Api.ApiClient;
import com.ricknotes.proquiz.Model.Constants;
import com.ricknotes.proquiz.Model.Questions;
import com.ricknotes.proquiz.Model.Result;
import com.ricknotes.proquiz.R;
import com.ricknotes.proquiz.Utils.Typewriter;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ricknotes.proquiz.Model.Constants.QUESTION_CATEGORY;
import static com.ricknotes.proquiz.Model.Constants.QUESTION_DIFFICULTY;
import static com.ricknotes.proquiz.Model.Constants.CORRECT_ANSWERS;
import static com.ricknotes.proquiz.Model.Constants.TOTAL_QUESTIONS;

public class QuestionsActivity extends AppCompatActivity {

    private static final String TAG = "QuestionsActivity";
    private TextView mQuestion, mAnswer;
    private Button mFirstOption, mSecondOption, mThirdOption, mFourthOption, mNext;
    private List<Questions> mQuestions;
    private int mScore, mTotalQuestions;
    private static int mQuestionNumber;
    private Api mApi;
    private String mCorrectAnswer;
    private String mCategory;
    private String mDifficulty;
    private boolean mChoiceSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        mApi = ApiClient.getInstance().create(Api.class);

        Intent intent = getIntent();
        mCategory = intent.getStringExtra(QUESTION_CATEGORY);
        mDifficulty = intent.getStringExtra(QUESTION_DIFFICULTY);

        initViews();

        getQuestions(mCategory, mDifficulty);
    }

    private void getQuestions(String category, String difficulty) {
        String type = "multiple";
        Call<Result> call = null;
        if (difficulty.equalsIgnoreCase("easy")){
           call = mApi.getQuestions(10, category,difficulty,type);
        }else if (difficulty.equalsIgnoreCase("medium")){
            call = mApi.getQuestions(20, category,difficulty,type);
        }else if (difficulty.equalsIgnoreCase("hard")){
            call = mApi.getQuestions(30, category,difficulty,type);
        }

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call,@NonNull Response<Result> response) {
                Result result = response.body();
                if (result != null){

                    mQuestions = result.getResults();
                    mTotalQuestions = mQuestions.size();
                    mQuestionNumber = 0;

                    updateViews();
                }

            }

            @Override
            public void onFailure( @NonNull Call<Result> call,@NonNull Throwable t) {
                Log.d(TAG, "!!!!!!!!!!!!!!onFailure:!!!!!!!!!!!!!!!!!! " + t.getLocalizedMessage());
                Toast.makeText(QuestionsActivity.this, "No network connection",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateViews() {
        setUp();

        if (mQuestionNumber == mQuestions.size() - 1){
            mNext.setText(R.string.string_finish);
        }
        String question = mQuestions.get(mQuestionNumber).getQuestion();
        mCorrectAnswer = mQuestions.get(mQuestionNumber).getCorrectAnswer();

        mQuestion.setText(Html.fromHtml(Html.fromHtml(question).toString()));

        String answerText = "Ans: " + mCorrectAnswer;
        mAnswer.setText(Html.fromHtml(Html.fromHtml(answerText).toString()));

        setOptions();
    }

    private void setUp() {
        mChoiceSelected = false;

        mFirstOption.setEnabled(true);
        mSecondOption.setEnabled(true);
        mThirdOption.setEnabled(true);
        mFourthOption.setEnabled(true);

        mFirstOption.setBackground(getDrawable(R.drawable.edit_text_background_shape));
        mSecondOption.setBackground(getDrawable(R.drawable.edit_text_background_shape));
        mThirdOption.setBackground(getDrawable(R.drawable.edit_text_background_shape));
        mFourthOption.setBackground(getDrawable(R.drawable.edit_text_background_shape));

        mAnswer.setVisibility(View.INVISIBLE);
    }

    private void setOptions() {
        List<String> options = mQuestions.get(mQuestionNumber).getIncorrectAnswer();
        options.add(mQuestions.get(mQuestionNumber).getCorrectAnswer());

        Collections.shuffle(options);

        mFirstOption.setText(Html.fromHtml(Html.fromHtml(options.get(0)).toString()));
        mSecondOption.setText(Html.fromHtml(Html.fromHtml(options.get(1)).toString()));
        mThirdOption.setText(Html.fromHtml(Html.fromHtml(options.get(2)).toString()));
        mFourthOption.setText(Html.fromHtml(Html.fromHtml(options.get(3)).toString()));
    }

    private void initViews() {
        mQuestion = findViewById(R.id.questions_activity_question_TV);
        mAnswer = findViewById(R.id.questions_activity_ans_TV);
        mFirstOption = findViewById(R.id.questions_activity_btn1);
        mSecondOption = findViewById(R.id.questions_activity_btn2);
        mThirdOption = findViewById(R.id.questions_activity_btn3);
        mFourthOption = findViewById(R.id.questions_activity_btn4);
        mNext = findViewById(R.id.questions_activity_next_btn);
    }

    public void nextQuestion(View view) {
        if (mChoiceSelected){
            if (mQuestionNumber != (mQuestions.size() - 1)){
                mQuestionNumber++;
                updateViews();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }else {
                Intent intent = new Intent(this, TotalScoreActivity.class);
                intent.putExtra(TOTAL_QUESTIONS, mTotalQuestions);
                intent.putExtra(CORRECT_ANSWERS, mScore);
                intent.putExtra(QUESTION_CATEGORY,mCategory);
                intent.putExtra(QUESTION_DIFFICULTY, mDifficulty);
                startActivity(intent);
                finish();
            }
        }else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }

    }

    public void selectAnswer(View view) {
        String choice = ((Button)view).getText().toString();
        boolean isCorrect = checkAnswer(choice);
        mChoiceSelected = true;

        if (isCorrect){
            mScore++;
            view.setBackground(getDrawable(R.drawable.correct_backgroind));
        }else {
            view.setBackground(getDrawable(R.drawable.wrong_background));

        }
        disableButtons();

        mAnswer.setVisibility(View.VISIBLE);
    }

    private void disableButtons() {
        mFirstOption.setEnabled(false);
        mSecondOption.setEnabled(false);
        mThirdOption.setEnabled(false);
        mFourthOption.setEnabled(false);
    }
    private boolean checkAnswer(String choice) {
        boolean isCorrect = false;
        if (choice.equalsIgnoreCase(mCorrectAnswer)){
            isCorrect = true;
        }
        return isCorrect;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Quit Session!")
                .setMessage("Are you sure you want to quit this session. You will lose your " +
                        "progress.")
                .setPositiveButton("Quit", (dialog1, which) -> {
                    Intent intent = new Intent(QuestionsActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Stay", (dialog12, which) -> dialog12.dismiss());
        dialog.show();

    }
}