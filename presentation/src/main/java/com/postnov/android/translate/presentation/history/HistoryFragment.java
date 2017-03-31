package com.postnov.android.translate.presentation.history;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.presentation.MainActivity;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class HistoryFragment extends BaseHistoryFragment<HistoryPresenter> {

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribeOnEvents();
        presenter.fetchHistory();
    }

    @Override
    public void showHistory(List<HistoryItem> items) {
        historyItemAdapter.swap(items);
        super.showHistory(items);
    }

    @Override
    protected void onClickDelete() {
        presenter.deleteAll(historyItemAdapter.getHistory());
    }

    @Override
    public void onItemClick(HistoryItem item) {
        presenter.addOrDeleteBookmark(item);
    }

    @Override
    protected void initInjection() {
        ((MainActivity) getActivity()).getComponent().inject(this);
    }
}
