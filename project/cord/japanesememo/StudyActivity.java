package com.example.japanesememo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StudyActivity extends AppCompatActivity {
    private TextView questionText, answerText, progressText;
    private Button showAnswerButton, nextButton, correctButton, wrongButton;
    private List<Word> studyWords;
    private Word currentWord;
    private int currentIndex = 0;
    private int correctCount = 0;
    private DatabaseHelper databaseHelper;
    private boolean isAnswerShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        initViews();
        setupDatabase();
        loadStudyWords();
        setupClickListeners();
        showNextWord();
    }

    private void initViews() {
        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);
        progressText = findViewById(R.id.progressText);
        showAnswerButton = findViewById(R.id.showAnswerButton);
        nextButton = findViewById(R.id.nextButton);
        correctButton = findViewById(R.id.correctButton);
        wrongButton = findViewById(R.id.wrongButton);
    }

    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void loadStudyWords() {
        studyWords = databaseHelper.getAllWords();
        Collections.shuffle(studyWords);
    }

    private void setupClickListeners() {
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswer();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextWord();
            }
        });

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markAsCorrect();
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markAsWrong();
            }
        });
    }

    private void showNextWord() {
        if (studyWords.isEmpty()) {
            Toast.makeText(this, "학습할 단어가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (currentIndex >= studyWords.size()) {
            showStudyResult();
            return;
        }

        currentWord = studyWords.get(currentIndex);
        questionText.setText(currentWord.getJapanese());
        answerText.setText("");
        answerText.setVisibility(View.GONE);

        progressText.setText((currentIndex + 1) + " / " + studyWords.size());

        showAnswerButton.setVisibility(View.VISIBLE);
        correctButton.setVisibility(View.GONE);
        wrongButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        isAnswerShown = false;
    }

    private void showAnswer() {
        answerText.setText(currentWord.getKorean() +
                (currentWord.getHiragana().isEmpty() ? "" : " (" + currentWord.getHiragana() + ")"));
        answerText.setVisibility(View.VISIBLE);

        showAnswerButton.setVisibility(View.GONE);
        correctButton.setVisibility(View.VISIBLE);
        wrongButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);

        isAnswerShown = true;
    }

    private void markAsCorrect() {
        correctCount++;
        currentWord.setStudyCount(currentWord.getStudyCount() + 1);
        if (currentWord.getStudyCount() >= 3) {
            currentWord.setLearned(true);
        }
        databaseHelper.updateWord(currentWord);

        currentIndex++;
        showNextWord();
    }

    private void markAsWrong() {
        currentWord.setStudyCount(currentWord.getStudyCount() + 1);
        databaseHelper.updateWord(currentWord);

        currentIndex++;
        showNextWord();
    }

    private void showStudyResult() {
        Toast.makeText(this, "학습 완료! 정답률: " + correctCount + "/" + studyWords.size(),
                Toast.LENGTH_LONG).show();
        finish();
    }
}