package com.postnov.android.translate.domain;

/**
 * @author Valentin Postnov
 */
public class Synonym {
    private String original;
    private String translate;

    public Synonym(String original, String translate) {
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
