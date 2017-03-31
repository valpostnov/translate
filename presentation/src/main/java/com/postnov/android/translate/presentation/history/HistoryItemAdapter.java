package com.postnov.android.translate.presentation.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.presentation.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Valentin Postnov
 */
class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemAdapter.ViewHolder> {

    private List<HistoryItem> historyItems;
    private OnBookmarkClickListener onBookmarkClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(historyItems.get(position));
    }

    @Override
    public int getItemCount() {
        return historyItems != null ? historyItems.size() : 0;
    }

    public List<HistoryItem> getHistory() {
        return historyItems;
    }

    void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        onBookmarkClickListener = listener;
    }

    void swap(List<HistoryItem> items) {
        historyItems = items;
        notifyDataSetChanged();
    }

    interface OnBookmarkClickListener {

        void onItemClick(HistoryItem item);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_history_original_text)
        TextView original;
        @BindView(R.id.item_synonym_translated_text)
        TextView translate;
        @BindView(R.id.item_history_lang)
        TextView lang;
        @BindView(R.id.item_history_bookmark)
        ToggleButton bookmark;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bookmark.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HistoryItem item = historyItems.get(getAdapterPosition());
            item.setBookmark(!item.isBookmark());
            onBookmarkClickListener.onItemClick(item);
        }

        void bind(HistoryItem item) {
            original.setText(item.getOriginal());
            translate.setText(item.getTranslated());
            lang.setText(item.getLang());
            bookmark.setChecked(item.isBookmark());
        }
    }
}
