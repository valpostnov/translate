package com.postnov.android.translate.presentation.history;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.presentation.base.BaseMvpView;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public interface HistoryView extends BaseMvpView {

    void showHistory(List<HistoryItem> items);

    void showFavorite(List<HistoryItem> items);
}
