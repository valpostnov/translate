package com.postnov.android.translate.data.entity;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class DictionaryEntity {

    private String text;
    private List<AlternateTranslateEntity> tr;

    public DictionaryEntity(String text, List<AlternateTranslateEntity> tr) {
        this.text = text;
        this.tr = tr;
    }

    public String getText() {
        return text;
    }

    public List<AlternateTranslateEntity> getAlternate() {
        return tr;
    }

    public String getTranslate() {
        return tr.size() != 0 ? tr.get(0).getText() : null;
    }
}
