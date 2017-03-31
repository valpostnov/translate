package com.postnov.android.translate.data.util;

import com.postnov.android.translate.data.util.LanguageHelperImpl.LanguagePair;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
public interface LanguageHelper {

    Observable<String> getOriginalLang();

    Observable<String> getTranslateLang();

    void swapLang();

    void setOriginal(String lang, String code);

    void setTranslate(String lang, String code);

    Observable<LanguagePair> getPair();
}
