package com.postnov.android.translate.data.entity;

/**
 * @author Valentin Postnov
 */
public class TranslateEntity {

    private String lang;
    private String[] text;

    public String getLang() {
        return lang;
    }

    public String getText() {
        return text[0];
    }
}
