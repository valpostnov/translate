package com.postnov.android.translate.presentation.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Valentin Postnov
 */
public final class UiUtils {

    private UiUtils() {
        //no instance
    }

    public static void showKeyboard(Context context, View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyboard(Context context, View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
