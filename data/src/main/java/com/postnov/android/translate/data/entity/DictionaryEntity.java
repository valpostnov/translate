package com.postnov.android.translate.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class DictionaryEntity {

    private String text;
    @SerializedName("tr")
    private List<AlternateTranslateEntity> translates;

    public DictionaryEntity(String text, List<AlternateTranslateEntity> translates) {
        this.text = text;
        this.translates = translates;
    }

    public String getText() {
        return text;
    }

    public List<AlternateTranslateEntity> getAlternate() {
        return translates;
    }

    public String getTranslate() {
        return translates.size() != 0 ? translates.get(0).getText() : null;
    }
}
