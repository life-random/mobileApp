package com.example.japanesememo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Word> wordList;
    private Context context;
    private DatabaseHelper databaseHelper;

    public WordAdapter(List<Word> wordList, Context context) {
        this.wordList = wordList;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.japaneseText.setText(word.getJapanese());
        holder.koreanText.setText(word.getKorean());
        holder.hiraganaText.setText(word.getHiragana());
        holder.studyCountText.setText("학습 횟수: " + word.getStudyCount());

        // 학습 완료 표시
        if (word.isLearned()) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteWord(word.getId());
                wordList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, wordList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    static class WordViewHolder extends RecyclerView.ViewHolder {
        TextView japaneseText, koreanText, hiraganaText, studyCountText;
        ImageButton deleteButton;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            japaneseText = itemView.findViewById(R.id.japaneseText);
            koreanText = itemView.findViewById(R.id.koreanText);
            hiraganaText = itemView.findViewById(R.id.hiraganaText);
            studyCountText = itemView.findViewById(R.id.studyCountText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
