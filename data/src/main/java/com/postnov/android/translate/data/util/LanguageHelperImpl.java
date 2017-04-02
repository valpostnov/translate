package com.postnov.android.translate.data.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
public class LanguageHelperImpl implements LanguageHelper {

    public static final String RU = "ru";
    public static final String EN = "en";
    public static final String ORIGINAL_LANG = "Русский";
    public static final String TRANSLATE_LANG = "Английский";

    static final String PREF_LANG_1_CODE = "pref_lang_1_code";
    static final String PREF_LANG_2_CODE = "pref_lang_2_code";

    private static final String PREF_LANG_1_ALIAS = "pref_lang_1_alias";
    private static final String PREF_LANG_2_ALIAS = "pref_lang_2_alias";

    private final SharedPreferences preferences;

    @Inject
    public LanguageHelperImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public Observable<String> getOriginalLang() {
        return Observable.just(preferences.getString(PREF_LANG_1_ALIAS, ORIGINAL_LANG));
    }

    public Observable<String> getTranslateLang() {
        return Observable.just(preferences.getString(PREF_LANG_2_ALIAS, TRANSLATE_LANG));
    }

    public void swapLang() {
        String langOriginalAlias = preferences.getString(PREF_LANG_1_ALIAS, ORIGINAL_LANG);
        String langTranslateAlias = preferences.getString(PREF_LANG_2_ALIAS, TRANSLATE_LANG);
        String langOriginalCode = preferences.getString(PREF_LANG_1_CODE, RU);
        String langTranslateCode = preferences.getString(PREF_LANG_2_CODE, EN);

        preferences.edit()
                .putString(PREF_LANG_1_ALIAS, langTranslateAlias)
                .putString(PREF_LANG_2_ALIAS, langOriginalAlias)
                .putString(PREF_LANG_1_CODE, langTranslateCode)
                .putString(PREF_LANG_2_CODE, langOriginalCode)
                .apply();
    }

    public void setOriginal(String lang, String code) {
        preferences.edit()
                .putString(PREF_LANG_1_CODE, code)
                .putString(PREF_LANG_1_ALIAS, lang).apply();
    }

    public void setTranslate(String lang, String code) {
        preferences.edit()
                .putString(PREF_LANG_2_CODE, code)
                .putString(PREF_LANG_2_ALIAS, lang)
                .apply();
    }

    @Override
    public Observable<LanguagePair> getPair() {
        return Observable.zip(getOriginalLang(), getTranslateLang(), LanguagePair::new);
    }

    public static class LanguagePair {

        private final String original;
        private final String translate;

        LanguagePair(String original, String translate) {
            this.original = original;
            this.translate = translate;
        }

        public String getOriginal() {
            return original;
        }

        public String getTranslate() {
            return translate;
        }
    }
}
