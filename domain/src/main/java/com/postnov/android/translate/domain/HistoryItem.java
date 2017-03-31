package com.postnov.android.translate.domain;

/**
 * @author Valentin Postnov
 */
public class HistoryItem implements Comparable<HistoryItem> {

    private String original;
    private String translated;
    private String lang;
    private boolean isBookmark;
    private boolean isHistory;
    private long timestamp;

    public HistoryItem() {
        this.isBookmark = false;
        this.isHistory = true;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(HistoryItem historyItem) {
        return Long.compare(timestamp, historyItem.timestamp);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public boolean isBookmark() {
        return isBookmark;
    }

    public void setBookmark(boolean fave) {
        isBookmark = fave;
    }
}
