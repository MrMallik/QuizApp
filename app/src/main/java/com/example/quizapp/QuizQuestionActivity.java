package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    private int mCurrentPosition = 1;
    private ArrayList<Question> mQuestionsList = null;
    private int mSelectedOptionPosition = 0;
    private int mCorrectAnswers = 0;
    private String mUsername = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        mUsername = getIntent().getStringExtra(Constants.USER_NAME);

        setQuestion();

        findViewById(R.id.tv_optionOne).setOnClickListener(this);
        findViewById(R.id.tv_optionTwo).setOnClickListener(this);
        findViewById(R.id.tv_optionThree).setOnClickListener(this);
        findViewById(R.id.tv_optionFour).setOnClickListener(this);
        findViewById(R.id.btnSubmit).setOnClickListener(this);

    }

    private void setQuestion(){
        mQuestionsList = Constants.getQuestions();
        

        Question ques = mQuestionsList.get(mCurrentPosition - 1);

        defaultOptionsView();

        if(mCurrentPosition == mQuestionsList.size()){
            Button btnSubmit = findViewById(R.id.btnSubmit);
            btnSubmit.setText("FINISH");
        }else{
            Button btnSubmit = findViewById(R.id.btnSubmit);
            btnSubmit.setText("SUBMIT");
        }

        //Update ProgressBar
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(mCurrentPosition);

        //Set Progress
        TextView tv_progress = findViewById(R.id.tv_progress);
        tv_progress.setText(mCurrentPosition + "/" + progressBar.getMax());

        //Set Question
        TextView tv_question = findViewById(R.id.tv_question);
        tv_question.setText(ques.getQuestion());

        //Set Image
        ImageView iv_quiz_image = findViewById(R.id.iv_quiz_image);
        iv_quiz_image.setImageResource(ques.getImage());

        //Set the questions
        TextView optionOne = findViewById(R.id.tv_optionOne);
        optionOne.setText(ques.getOptionOne());
        TextView optionTwo = findViewById(R.id.tv_optionTwo);
        optionTwo.setText(ques.getOptionTwo());
        TextView optionThree = findViewById(R.id.tv_optionThree);
        optionThree.setText(ques.getOptionThree());
        TextView optionFour = findViewById(R.id.tv_optionFour);
        optionFour.setText(ques.getOptionFour());
    }

    private void defaultOptionsView(){
        ArrayList<TextView> options = new ArrayList<>();
        options.add(0, findViewById(R.id.tv_optionOne));
        options.add(1, findViewById(R.id.tv_optionTwo));
        options.add(2, findViewById(R.id.tv_optionThree));
        options.add(3, findViewById(R.id.tv_optionFour));

        for(TextView option : options){
            option.setTextColor(Color.parseColor("#7A8089"));
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.default_option_border_bg
            ));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_optionOne : selectedOptionView(findViewById(R.id.tv_optionOne),
                    1); break;
            case R.id.tv_optionTwo : selectedOptionView(findViewById(R.id.tv_optionTwo),
                    2); break;
            case R.id.tv_optionThree : selectedOptionView(findViewById(R.id.tv_optionThree),
                    3); break;
            case R.id.tv_optionFour : selectedOptionView(findViewById(R.id.tv_optionFour),
                    4); break;
            case R.id.btnSubmit :
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++;

                    if(mCurrentPosition <= mQuestionsList.size())
                        setQuestion();
                    else{
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

                        intent.putExtra(Constants.USER_NAME, mUsername);
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers);
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size());

                        startActivity(intent);
                        finish();
                    }

            }else{
                    Question question = mQuestionsList.get(mCurrentPosition - 1);
                    if(question.getCorrectAnswer() != mSelectedOptionPosition)
                        answerView(mSelectedOptionPosition, R.drawable.incorrect_option_border_bg);
                    else
                        mCorrectAnswers++;

                    answerView(question.getCorrectAnswer(), R.drawable.correct_option_border_bg);

                    if(mCurrentPosition == mQuestionsList.size()){
                        Button btnSubmit = findViewById(R.id.btnSubmit);
                        btnSubmit.setText("FINISH");
                    }else{
                        Button btnSubmit = findViewById(R.id.btnSubmit);
                        btnSubmit.setText("GO TO NEXT QUESTION");
                    }

                    mSelectedOptionPosition = 0;

                }
        }
    }

    private void answerView(int answer, int drawableView){
        switch(answer){
            case 1 : findViewById(R.id.tv_optionOne).setBackground(ContextCompat.getDrawable(
                    this, drawableView
            )); break;
            case 2 : findViewById(R.id.tv_optionTwo).setBackground(ContextCompat.getDrawable(
                    this, drawableView
            )); break;
            case 3 : findViewById(R.id.tv_optionThree).setBackground(ContextCompat.getDrawable(
                    this, drawableView
            )); break;
            case 4 : findViewById(R.id.tv_optionFour).setBackground(ContextCompat.getDrawable(
                    this, drawableView
            )); break;
        }
    }

    private void selectedOptionView(TextView tv, int selectedOptionNumber){
        defaultOptionsView();
        mSelectedOptionPosition = selectedOptionNumber;

        tv.setTextColor(Color.parseColor("#7A8089"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(
                getApplicationContext(),
                R.drawable.selected_option_border_bg
        ));
    }
}