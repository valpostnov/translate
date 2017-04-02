package com.postnov.android.translate.presentation.language;

import com.postnov.android.translate.data.util.LanguageHelper;
import com.postnov.android.translate.presentation.base.BaseMvpPresenter;

import javax.inject.Inject;

import static com.postnov.android.translate.data.util.LanguageHelperImpl.EN;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.ORIGINAL_LANG;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.RU;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.TRANSLATE_LANG;
import static com.postnov.android.translate.presentation.main.MainFragment.CHOOSE_ORIGINAL_LANG_ACTION;

/**
 * @author Valentin Postnov
 */
class LanguagePresenter extends BaseMvpPresenter<LanguageView> {

    private final LanguageHelper languageHelper;

    @Inject
    LanguagePresenter(LanguageHelper languageHelper) {
        this.languageHelper = languageHelper;
    }

    void updateOriginal(String action) {
        switch (action) {
            case CHOOSE_ORIGINAL_LANG_ACTION:
                languageHelper.setOriginal(ORIGINAL_LANG, RU);
                break;
            default:
                languageHelper.setTranslate(ORIGINAL_LANG, RU);

        }
    }

    void updateTranslate(String action) {
        switch (action) {
            case CHOOSE_ORIGINAL_LANG_ACTION:
                languageHelper.setOriginal(TRANSLATE_LANG, EN);
                break;
            default:
                languageHelper.setTranslate(TRANSLATE_LANG, EN);
        }
    }
}
