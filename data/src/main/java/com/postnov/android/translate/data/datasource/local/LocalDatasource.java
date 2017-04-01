package com.postnov.android.translate.data.datasource.local;

import com.postnov.android.translate.domain.HistoryItem;

import java.util.List;

import rx.Completable;
import rx.Single;

/**
 * @author Valentin Postnov
 */
public interface LocalDatasource {

    Single<List<HistoryItem>> getHistory();

    Single<List<HistoryItem>> getFavorite();

    void saveOrUpdate(HistoryItem item);

    Completable deleteBookmarks(List<HistoryItem> items);

    Completable markHistoryForDelete(List<HistoryItem> items);

    Completable deleteHistory();
}
