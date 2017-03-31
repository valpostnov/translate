package com.postnov.android.translate.data.entity;

/**
 * @author Valentin Postnov
 */
public class AlternateTranslateEntity {

    private String text;
    private TranslateInnerObject[] syn;
    private TranslateInnerObject[] mean;

    public String getText() {
        return text;
    }

    public TranslateInnerObject[] getSynonyms() {
        return syn;
    }

    public TranslateInnerObject[] getMeans() {
        return mean;
    }
}
