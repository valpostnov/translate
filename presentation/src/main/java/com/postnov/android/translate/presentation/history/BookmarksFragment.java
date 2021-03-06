package com.postnov.android.translate.presentation.history;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.presentation.MainActivity;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class BookmarksFragment extends BaseHistoryFragment<BookmarksPresenter> {

    public static BookmarksFragment newInstance() {
        return new BookmarksFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribeOnDBChangeEvents();
        presenter.fetchBookmarks();
    }

    @Override
    public void showBookmarks(List<HistoryItem> items) {
        historyItemAdapter.swap(items);
        super.showBookmarks(items);
    }

    protected void onClickDelete() {
        presenter.delete(historyItemAdapter.getHistory());
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
