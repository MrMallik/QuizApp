package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        String userName = getIntent().getStringExtra(Constants.USER_NAME);
        int correctAnswers = getIntent().getIntExtra(Constants.CORRECT_ANSWERS, 0);
        int totalquestions = getIntent().getIntExtra(Constants.TOTAL_QUESTIONS, 0);

        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_score = findViewById(R.id.tv_score);

        tv_name.setText(userName);
        tv_score.setText("You score is " + correctAnswers + " out of " + totalquestions);

        Button btn_finish = findViewById(R.id.btn_finish);

        btn_finish.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}