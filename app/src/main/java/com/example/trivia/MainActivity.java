 package com.example.trivia;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;
import static com.google.android.material.snackbar.Snackbar.make;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.example.trivia.model.Score;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    private int currentScore = 0;
    List<Question> questions;
    Score score = new Score();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         questions = new Repository().getQuestions(questionArrayList ->
                 binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex)
                         .getAnswer()));

         binding.buttonNext.setOnClickListener(view -> {
             currentQuestionIndex = (currentQuestionIndex + 1);
             updateQuestion();
         });

         binding.buttonTrue.setOnClickListener(view -> {
            checkAnswer(questions, true);

         });

         binding.buttonFalse.setOnClickListener(view -> {
            checkAnswer(questions, false);
         });

         binding.score.setText(String.valueOf(score.getScore()));
     }

     private void updateQuestion() {
         String quesiton = questions.get(currentQuestionIndex).getAnswer();
         binding.questionTextView.setText(quesiton);
     }

     private void checkAnswer(List<Question> questionArrayList, boolean clicked){
         Boolean answer = questionArrayList.get(currentQuestionIndex).isAnswerTrue();
         int snackbarId;
         if(answer == clicked) {
            snackbarId = R.string.correct;
            addPoints();
         } else {
             snackbarId = R.string.incorrect;
             deductPoints();
         }
         Snackbar.make(binding.questionTextView, snackbarId, LENGTH_SHORT )
                 .show();
     };

     private void addPoints() {
                 currentScore += 100;
                 score.setScore(currentScore);
                 binding.score.setText(String.valueOf(score.getScore()));
     }

     private void deductPoints(){
         currentScore -= 100;
         score.setScore(currentScore);
         binding.score.setText(String.valueOf(score.getScore()));
     }


 }