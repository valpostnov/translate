package com.postnov.android.translate.data.util;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * @author Valentin Postnov
 */
public class PreferencesManager {

    public static final String PREF_LANG_1_ALIAS = "pref_lang_1_alias";
    public static final String PREF_LANG_2_ALIAS = "pref_lang_2_alias";

    public static final String PREF_LANG_1_CODE = "pref_lang_1_code";
    public static final String PREF_LANG_2_CODE = "pref_lang_2_code";

    public static final String PREF_LANG_DEF_VALUE = "ru-en";

    private final SharedPreferences preferences;

    @Inject
    public PreferencesManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key, @Nullable String defValue) {
        return preferences.getString(key, defValue);
    }
}
