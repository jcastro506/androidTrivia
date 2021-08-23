 package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.AnswerListASyncResponse;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questions;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questions = new Repository().getQuestions(questionArrayList ->
                binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex)
                        .getAnswer()));

        binding.buttonNext.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
            updateQuestion();
        });

        binding.buttonTrue.setOnClickListener(view -> {

        });

        binding.buttonFalse.setOnClickListener(view -> {

        });
    }

     private void updateQuestion() {
         String quesiton = questions.get(currentQuestionIndex).getAnswer();
         binding.questionTextView.setText(quesiton);
     }

 }