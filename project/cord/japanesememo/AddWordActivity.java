package com.example.japanesememo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddWordActivity extends AppCompatActivity implements JishoApiService.ApiCallback, TranslationService.TranslationCallback {
    private EditText japaneseEditText, koreanEditText, hiraganaEditText, searchEditText;
    private Button saveButton, cancelButton, searchButton;
    private RecyclerView searchResultsRecyclerView;
    private ProgressBar progressBar;
    private DatabaseHelper databaseHelper;
    private JishoApiService apiService;
    private TranslationService translationService;
    private SearchResultAdapter searchResultAdapter;
    private LocalSearchAdapter localSearchAdapter;
    private List<JishoWord> searchResults;
    private List<Word> localSearchResults;
    private boolean isLocalSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        initViews();
        setupDatabase();
        setupServices();
        setupRecyclerView();
        setupClickListeners();
    }

    private void initViews() {
        japaneseEditText = findViewById(R.id.japaneseEditText);
        koreanEditText = findViewById(R.id.koreanEditText);
        hiraganaEditText = findViewById(R.id.hiraganaEditText);
        searchEditText = findViewById(R.id.searchEditText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        searchButton = findViewById(R.id.searchButton);
        searchResultsRecyclerView = findViewById(R.id.searchResultsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }

    private void setupServices() {
        apiService = new JishoApiService();
        translationService = new TranslationService();
    }

    private void setupRecyclerView() {
        searchResults = new ArrayList<>();
        localSearchResults = new ArrayList<>();

        searchResultAdapter = new SearchResultAdapter(searchResults, new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(JishoWord word) {
                fillWordFields(word);
            }
        });

        localSearchAdapter = new LocalSearchAdapter(localSearchResults, new LocalSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Word word) {
                fillLocalWordFields(word);
            }
        });

        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecyclerView.setAdapter(searchResultAdapter);
    }

    private void setupClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWord();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord();
            }
        });
    }

    private void searchWord() {
        String searchQuery = searchEditText.getText().toString().trim();
        if (searchQuery.isEmpty()) {
            Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        searchResultsRecyclerView.setVisibility(View.GONE);

        // 한글인지 확인
        if (TranslationService.isKorean(searchQuery)) {
            // 먼저 로컬 DB에서 검색
            searchLocalDatabase(searchQuery);

            // 그 다음 번역해서 온라인 검색
            translationService.translateKoreanToEnglish(searchQuery, this);
        } else {
            // 일본어나 영어면 바로 온라인 검색
            isLocalSearch = false;
            apiService.searchWord(searchQuery, this);
        }
    }

    private void searchLocalDatabase(String koreanQuery) {
        List<Word> localResults = databaseHelper.searchWordsByKorean(koreanQuery);

        if (!localResults.isEmpty()) {
            isLocalSearch = true;
            localSearchResults.clear();
            localSearchResults.addAll(localResults);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    searchResultsRecyclerView.setAdapter(localSearchAdapter);
                    localSearchAdapter.notifyDataSetChanged();
                    searchResultsRecyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(AddWordActivity.this,
                            "저장된 단어에서 " + localResults.size() + "개 찾음", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void fillWordFields(JishoWord word) {
        japaneseEditText.setText(word.getJapanese());
        koreanEditText.setText(word.getKorean());
        hiraganaEditText.setText(word.getReading());
    }

    private void fillLocalWordFields(Word word) {
        japaneseEditText.setText(word.getJapanese());
        koreanEditText.setText(word.getKorean());
        hiraganaEditText.setText(word.getHiragana());
    }

    private void saveWord() {
        String japanese = japaneseEditText.getText().toString().trim();
        String korean = koreanEditText.getText().toString().trim();
        String hiragana = hiraganaEditText.getText().toString().trim();

        if (japanese.isEmpty() || korean.isEmpty()) {
            Toast.makeText(this, "일본어와 한국어는 필수 입력사항입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Word word = new Word(japanese, korean, hiragana);
        long result = databaseHelper.insertWord(word);

        if (result != -1) {
            Toast.makeText(this, "단어가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // JishoApiService 콜백 메서드들
    @Override
    public void onSuccess(List<JishoWord> words) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                isLocalSearch = false;
                searchResults.clear();
                searchResults.addAll(words);

                searchResultsRecyclerView.setAdapter(searchResultAdapter);
                searchResultAdapter.notifyDataSetChanged();

                if (words.isEmpty()) {
                    Toast.makeText(AddWordActivity.this, "온라인 검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    searchResultsRecyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(AddWordActivity.this,
                            "온라인에서 " + words.size() + "개 찾음", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onError(String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddWordActivity.this, "온라인 검색 중 오류: " + error,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TranslationService 콜백 메서드들
    @Override
    public void onTranslationSuccess(String translatedText) {
        // 번역된 영어로 Jisho API 검색
        apiService.searchWord(translatedText, this);
    }

    @Override
    public void onTranslationError(String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddWordActivity.this, "번역 실패: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}