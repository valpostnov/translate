package com.postnov.android.translate.data.entity.dictionary;

/**
 * @author Valentin Postnov
 */
public class DefTr {

    private String text;
    private TranslateInnerObject[] syn;
    private TranslateInnerObject[] mean;

    public String getText() {
        return text;
    }

    public TranslateInnerObject[] getSynonym() {
        return syn;
    }

    public TranslateInnerObject[] getMean() {
        return mean;
    }
}
