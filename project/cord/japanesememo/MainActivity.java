package com.example.japanesememo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WordAdapter wordAdapter;
    private List<Word> wordList;
    private Button addWordButton, studyButton;
    private TextView totalWordsText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        setupDatabase();
        loadWords();
        updateWordCount();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        addWordButton = findViewById(R.id.addWordButton);
        studyButton = findViewById(R.id.studyButton);
        totalWordsText = findViewById(R.id.totalWordsText);

        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView() {
        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(wordList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wordAdapter);
    }

    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void loadWords() {
        wordList.clear();
        wordList.addAll(databaseHelper.getAllWords());
        wordAdapter.notifyDataSetChanged();
    }

    private void updateWordCount() {
        totalWordsText.setText("총 단어 수: " + wordList.size() + "개");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWords();
        updateWordCount();
    }
}