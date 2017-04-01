package com.postnov.android.translate.data.datasource.local;

import com.postnov.android.translate.data.datasource.local.table.HistoryItemTable;
import com.postnov.android.translate.domain.HistoryItem;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Single;

import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.QUERY_FAVE;
import static com.postnov.android.translate.data.datasource.local.table.HistoryItemTable.QUERY_HISTORY;

/**
 * @author Valentin Postnov
 */
public class LocalDatasourceImpl implements LocalDatasource {

    private final StorIOSQLite storIOSQLite;

    @Inject
    public LocalDatasourceImpl(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    @Override
    public Single<List<HistoryItem>> getHistory() {
        return storIOSQLite
                .get()
                .listOfObjects(HistoryItem.class)
                .withQuery(QUERY_HISTORY)
                .prepare()
                .asRxSingle();
    }

    @Override
    public Single<List<HistoryItem>> getFavorite() {
        return storIOSQLite.get()
                .listOfObjects(HistoryItem.class)
                .withQuery(QUERY_FAVE)
                .prepare()
                .asRxSingle();
    }

    @Override
    public void saveOrUpdate(HistoryItem item) {
        storIOSQLite.put()
                .object(item)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public Completable deleteBookmarks(List<HistoryItem> items) {
        return storIOSQLite
                .put()
                .objects(items)
                .prepare()
                .asRxCompletable();
    }

    @Override
    public Completable markHistoryForDelete(List<HistoryItem> items) {
        return storIOSQLite.put()
                .objects(items)
                .prepare()
                .asRxCompletable();
    }

    @Override
    public Completable deleteHistory() {
        return storIOSQLite
                .delete()
                .byQuery(HistoryItemTable.QUERY_DELETE_HISTORY)
                .prepare()
                .asRxCompletable();
    }
}
