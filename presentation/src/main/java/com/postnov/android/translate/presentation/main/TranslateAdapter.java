package com.postnov.android.translate.presentation.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.Synonym;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.presentation.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Valentin Postnov
 */
class TranslateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_TYPE = 11;
    private static final int SYNONYM_TYPE = 12;

    private Translate translate;
    private OnBookmarkClickListener onBookmarkClickListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case HEADER_TYPE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_translate, viewGroup, false);
                return new HeaderViewHolder(view);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_synonym, viewGroup, false);
                return new SynonymViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (position) {
            case 0:
                ((HeaderViewHolder) viewHolder).bind(translate.getHistoryItem());
                break;
            default:
                ((SynonymViewHolder) viewHolder).bind(translate.getSynonyms().get(position - 1));

        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return HEADER_TYPE;
            default:
                return SYNONYM_TYPE;

        }
    }

    @Override
    public int getItemCount() {
        return translate != null ? translate.getSynonyms().size() + 1 : 0;
    }

    void swap(Translate translate) {
        this.translate = translate;
        notifyDataSetChanged();
    }

    Translate getItem() {
        return translate;
    }

    void clear() {
        if (translate != null) {
            translate = null;
            notifyDataSetChanged();
        }
    }

    void swap(HistoryItem historyItem) {
        if (translate != null && historyItem.getTimestamp() == translate.getHistoryItem().getTimestamp()) {
            translate.setHistoryItem(historyItem);
            notifyItemChanged(0);
        }
    }

    void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        onBookmarkClickListener = listener;
    }

    interface OnBookmarkClickListener {

        void onBookmarkClick(HistoryItem item);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_translate_original_text)
        TextView original;
        @BindView(R.id.item_translate_translated_text)
        TextView translated;
        @BindView(R.id.item_translate_bookmark)
        ToggleButton bookmark;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bookmark.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HistoryItem item = translate.getHistoryItem();
            item.setBookmark(!item.isBookmark());
            onBookmarkClickListener.onBookmarkClick(item);
        }

        void bind(HistoryItem historyItem) {
            original.setText(historyItem.getOriginal());
            translated.setText(historyItem.getTranslated());
            bookmark.setChecked(historyItem.isBookmark());
        }
    }

    class SynonymViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_synonym_original_text)
        TextView original;
        @BindView(R.id.item_synonym_translated_text)
        TextView translated;

        SynonymViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Synonym synonym) {
            original.setText(synonym.getOriginal());
            translated.setText(synonym.getTranslate());
            original.setVisibility(synonym.getOriginal() == null ? View.GONE : View.VISIBLE);
            translated.setVisibility(synonym.getTranslate() == null ? View.GONE : View.VISIBLE);
        }
    }
}
