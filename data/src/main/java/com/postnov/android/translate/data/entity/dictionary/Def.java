package com.postnov.android.translate.data.entity.dictionary;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class Def {

    private String text;
    private List<DefTr> tr;

    public Def(String text, List<DefTr> defTrs) {
        this.text = text;
        this.tr = defTrs;
    }

    public String getText() {
        return text;
    }

    public List<DefTr> getTranslates() {
        return tr;
    }
}
