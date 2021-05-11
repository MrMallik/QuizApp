package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This sets the Activity to Full Screen
        //In our case, it removes the status bar from the Welcome Screen of our App
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


        //Here's how we implement onClickListeners
        //Get the button
        //Call setOnClickListener
        //Create new  View OnClickListener
        //Write our handler code in onClick method

        Button btnStart = findViewById(R.id.btnStart);
        EditText editTextName = findViewById(R.id.editTextName);
        btnStart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(editTextName.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                else{
                    //Intents are messaging objects used for communication
                    //Here we start the QuizQuestionActivity
                    Intent intent = new Intent(getApplicationContext(), QuizQuestionActivity.class);

                    intent.putExtra(Constants.USER_NAME, editTextName.getText().toString());

                    //Start the new activity
                    startActivity(intent);

                    //Close the current activity
                    finish();
                }
            }
        });
    }
}