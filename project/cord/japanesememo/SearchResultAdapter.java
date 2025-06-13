package com.example.japanesememo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<JishoWord> searchResults;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(JishoWord word);
    }

    public SearchResultAdapter(List<JishoWord> searchResults, OnItemClickListener listener) {
        this.searchResults = searchResults;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JishoWord word = searchResults.get(position);
        holder.japaneseText.setText(word.getJapanese());
        holder.readingText.setText(word.getReading());
        holder.meaningText.setText(word.getKorean());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(word);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView japaneseText, readingText, meaningText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            japaneseText = itemView.findViewById(R.id.searchJapaneseText);
            readingText = itemView.findViewById(R.id.searchReadingText);
            meaningText = itemView.findViewById(R.id.searchMeaningText);
        }
    }
}