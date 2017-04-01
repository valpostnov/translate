package com.postnov.android.translate.domain.repository;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.Translate;

import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * @author Valentin Postnov
 */
public interface TranslateRepository {

    Observable<Translate> getTranslate(String text);

    Single<List<HistoryItem>> getHistory();

    Single<List<HistoryItem>> getFavorite();

    Completable deleteBookmarks(List<HistoryItem> items);

    Completable markHistoryForDelete(List<HistoryItem> items);

    Completable addOrDeleteBookmark(HistoryItem item);

    Completable deleteHistory();
}
