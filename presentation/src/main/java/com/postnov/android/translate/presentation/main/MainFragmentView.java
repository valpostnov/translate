package com.postnov.android.translate.presentation.main;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.presentation.base.BaseMvpView;

/**
 * @author Valentin Postnov
 */
public interface MainFragmentView extends BaseMvpView {

    void showTranslate(Translate translate);

    void showProgress();

    void hideProgress();

    void showError(String error);

    void updateHistoryItem(HistoryItem item);

    void setDefLanguages(String original, String translate);
}
