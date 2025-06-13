package com.example.japanesememo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LocalSearchAdapter extends RecyclerView.Adapter<LocalSearchAdapter.ViewHolder> {
    private List<Word> localSearchResults;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Word word);
    }

    public LocalSearchAdapter(List<Word> localSearchResults, OnItemClickListener listener) {
        this.localSearchResults = localSearchResults;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_local_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = localSearchResults.get(position);
        holder.japaneseText.setText(word.getJapanese());
        holder.readingText.setText(word.getHiragana());
        holder.meaningText.setText(word.getKorean());
        holder.studyCountText.setText("학습 " + word.getStudyCount() + "회");

        // 학습 완료된 단어 표시
        if (word.isLearned()) {
            holder.itemView.setBackgroundColor(
                    holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light, null));
        } else {
            holder.itemView.setBackgroundColor(
                    holder.itemView.getContext().getResources().getColor(android.R.color.white, null));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(word);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localSearchResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView japaneseText, readingText, meaningText, studyCountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            japaneseText = itemView.findViewById(R.id.localJapaneseText);
            readingText = itemView.findViewById(R.id.localReadingText);
            meaningText = itemView.findViewById(R.id.localMeaningText);
            studyCountText = itemView.findViewById(R.id.localStudyCountText);
        }
    }
}