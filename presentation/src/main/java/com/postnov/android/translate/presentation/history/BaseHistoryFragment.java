package com.postnov.android.translate.presentation.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.base.BaseFragment;
import com.postnov.android.translate.presentation.base.BaseMvpPresenter;

import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * @author Valentin Postnov
 */
public abstract class BaseHistoryFragment<P extends BaseMvpPresenter> extends BaseFragment<P> implements HistoryView, HistoryItemAdapter.OnBookmarkClickListener {

    protected HistoryItemAdapter historyItemAdapter;

    @BindView(R.id.fragment_history_recycler)
    RecyclerView rv;
    @BindView(R.id.fragment_history_empty_view)
    View emptyView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyItemAdapter = new HistoryItemAdapter();
        historyItemAdapter.setOnBookmarkClickListener(this);
        initRecyclerView();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_history;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void showHistory(List<HistoryItem> items) {
        emptyView.setVisibility(historyItemAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showBookmarks(List<HistoryItem> items) {
        emptyView.setVisibility(historyItemAdapter.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    protected abstract void onClickDelete();

    private void initRecyclerView() {
        Context context = getContext();
        DividerItemDecoration divider = new DividerItemDecoration(context, VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider));

        rv.setAdapter(historyItemAdapter);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(divider);
    }
}
