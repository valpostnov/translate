package com.postnov.android.translate.data.util;

import android.content.SharedPreferences;

import javax.inject.Inject;

import rx.Observable;

import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_1_ALIAS;
import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_1_CODE;
import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_2_ALIAS;
import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_2_CODE;

/**
 * @author Valentin Postnov
 */
public class LanguageHelperImpl implements LanguageHelper {

    private final SharedPreferences preferences;

    @Inject
    public LanguageHelperImpl(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public Observable<String> getOriginalLang() {
        return Observable.just(preferences.getString(PREF_LANG_1_ALIAS, null));
    }

    public Observable<String> getTranslateLang() {
        return Observable.just(preferences.getString(PREF_LANG_2_ALIAS, null));
    }

    public void swapLang() {
        String langOriginalAlias = preferences.getString(PREF_LANG_1_ALIAS, null);
        String langTranslateAlias = preferences.getString(PREF_LANG_2_ALIAS, null);
        String langOriginalCode = preferences.getString(PREF_LANG_1_CODE, null);
        String langTranslateCode = preferences.getString(PREF_LANG_2_CODE, null);

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

        public LanguagePair(String original, String translate) {
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
