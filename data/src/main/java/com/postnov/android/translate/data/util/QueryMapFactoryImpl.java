package com.postnov.android.translate.data.util;

import android.support.annotation.NonNull;

import com.postnov.android.translate.domain.QueryMapFactory;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_1_CODE;
import static com.postnov.android.translate.data.util.PreferencesManager.PREF_LANG_2_CODE;

/**
 * @author Valentin Postnov
 */
public class QueryMapFactoryImpl implements QueryMapFactory {

    public static final String LANG = "lang";
    public static final String TEXT = "text";
    private static final String DICT_KEY = "dict";
    private static final String TRANS_KEY = "trns";
    private static final String KEY = "key";
    private final PreferencesManager preferencesManager;
    private final Map<String, String> dictQueryMap;
    private final Map<String, String> transQueryMap;

    @Inject
    public QueryMapFactoryImpl(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
        dictQueryMap = new TreeMap<>();
        dictQueryMap.put(KEY, DICT_KEY);
        transQueryMap = new TreeMap<>();
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
        return preferencesManager.getString(PREF_LANG_1_CODE, "ru") + "-"
                + preferencesManager.getString(PREF_LANG_2_CODE, "en");
    }
}
