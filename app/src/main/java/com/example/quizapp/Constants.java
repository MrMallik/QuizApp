package com.example.quizapp;

import java.util.ArrayList;

public class Constants {

    static final String USER_NAME = "user_name";
    static final String TOTAL_QUESTIONS = "total_questions";
    static final String CORRECT_ANSWERS = "correct_answers";

    public static ArrayList<Question> getQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();

        Question q1 = new Question(1,
                "Which country does this flag belong to?",
                R.drawable.ic_flag_of_argentina,
                "Argentina",
                "Australia",
                "Armenia",
                "India",
                1);

        questionList.add(q1);

        Question q2 = new Question(2,
                "Which country does this flag belong to?",
                R.drawable.ic_flag_of_australia,
                "Pakistan",
                "China",
                "Armenia",
                "Australia",
                4);

        questionList.add(q2);

        Question q3 = new Question(3,
                "Which country does this flag belong to?",
                R.drawable.ic_flag_of_belgium,
                "Argentina",
                "Belgium",
                "Bangladesh",
                "India",
                2);

        questionList.add(q3);

        Question q4 = new Question(4,
                "What was Google Inc. previously known as?",
                R.drawable.ic_google,
                "SmallTalk",
                "BackRub",
                "Hardwell",
                "Backjoint",
                2);

        questionList.add(q4);

        Question q5 = new Question(5,
                "When was Android Released?",
                R.drawable.ic_android,
                "2008",
                "2007",
                "2009",
                "2006",
                1);

        questionList.add(q5);

        return questionList;
    }
}
