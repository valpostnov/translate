package com.postnov.android.translate.data.util;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.postnov.android.translate.domain.QueryMapFactory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.postnov.android.translate.data.util.LanguageHelperImpl.EN;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.PREF_LANG_1_CODE;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.PREF_LANG_2_CODE;
import static com.postnov.android.translate.data.util.LanguageHelperImpl.RU;

/**
 * @author Valentin Postnov
 */
public class QueryMapFactoryImpl implements QueryMapFactory {

    public static final String LANG = "lang";
    public static final String TEXT = "text";

    private static final String DICT_KEY = "dict";
    private static final String TRANS_KEY = "trns";
    private static final String KEY = "key";

    private final SharedPreferences preferences;
    private final Map<String, String> dictQueryMap;
    private final Map<String, String> transQueryMap;

    @Inject
    public QueryMapFactoryImpl(SharedPreferences preferences) {
        this.preferences = preferences;
        dictQueryMap = new HashMap<>(3);
        dictQueryMap.put(KEY, DICT_KEY);
        transQueryMap = new HashMap<>(3);
        transQueryMap.put(KEY, TRANS_KEY);
    }

    @Override
    public Map<String, String> createForTrans(@NonNull String text) {
        transQueryMap.put(LANG, createLangPath());
        transQueryMap.put(TEXT, text);
        return transQueryMap;
    }

    @Override
    public Map<String, String> createForDict(String text) {
        dictQueryMap.put(LANG, createLangPath());
        dictQueryMap.put(TEXT, text);
        return dictQueryMap;
    }

    private String createLangPath() {
        return preferences.getString(PREF_LANG_1_CODE, RU) + "-"
                + preferences.getString(PREF_LANG_2_CODE, EN);
    }
}
