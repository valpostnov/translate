package com.postnov.android.translate.domain;

import java.util.List;

/**
 * @author Valentin Postnov
 */
public class Translate {

    private final List<Synonym> synonyms;
    private HistoryItem historyItem;

    private Translate(String lang, String original, String translate, List<Synonym> synonyms) {
        historyItem = new HistoryItem();
        historyItem.setLang(lang);
        historyItem.setOriginal(original);
        historyItem.setTranslated(translate);
        this.synonyms = synonyms;
    }

    public HistoryItem getHistoryItem() {
        return historyItem;
    }

    public void setHistoryItem(HistoryItem historyItem) {
        this.historyItem = historyItem;
    }

    public String getLang() {
        return historyItem.getLang();
    }

    public String getOriginal() {
        return historyItem.getOriginal();
    }

    public String getTranslate() {
        return historyItem.getTranslated();
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public static class Builder {
        private String lang;
        private String original;
        private String translate;
        private List<Synonym> synonyms;

        public Builder setTranslate(String translate) {
            this.translate = translate;
            return this;
        }

        public Builder setOriginal(String original) {
            this.original = original;
            return this;
        }

        public Builder setLang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder setSynonyms(List<Synonym> synonyms) {
            this.synonyms = synonyms;
            return this;
        }

        public Translate build() {
            return new Translate(lang, original, translate, synonyms);
        }
    }
}
