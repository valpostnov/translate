package com.postnov.android.translate.data.repository;

import com.postnov.android.translate.data.datasource.local.LocalDatasource;
import com.postnov.android.translate.data.datasource.remote.RemoteDatasource;
import com.postnov.android.translate.data.util.CollectionUtils;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.QueryMapFactory;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.functions.Action1;

/**
 * @author Valentin Postnov
 */
public class TranslateRepositoryImpl implements TranslateRepository {

    private final QueryMapFactory queryFactory;
    private final LocalDatasource localDatasource;
    private final Action1<Translate> saveTranslate;
    private final RemoteDatasource dictionary;
    private final RemoteDatasource translate;

    @Inject
    public TranslateRepositoryImpl(LocalDatasource localDatasource, QueryMapFactory queryFactory,
                                   @Named("dictionary") RemoteDatasource dictionary, @Named("translate") RemoteDatasource translate) {
        this.queryFactory = queryFactory;
        this.localDatasource = localDatasource;
        this.dictionary = dictionary;
        this.translate = translate;
        saveTranslate = this::addOrUpdate;
    }

    @Override
    public Observable<Translate> getTranslate(final String query) {
        return fromDictionarySource(query)
                .switchIfEmpty(fromTranslateSource(query))
                .doOnNext(saveTranslate);
    }

    @Override
    public Single<List<HistoryItem>> getHistory() {
        return localDatasource.getHistory();
    }

    @Override
    public Single<List<HistoryItem>> getFavorite() {
        return localDatasource.getFavorite();
    }

    @Override
    public Completable deleteBookmarks(List<HistoryItem> items) {
        CollectionUtils.doOnEach(items, item -> item.setBookmark(false));
        return localDatasource.deleteBookmarks(items);
    }

    @Override
    public Completable markHistoryForDelete(List<HistoryItem> items) {
        CollectionUtils.doOnEach(items, item -> item.setHistory(false));
        return localDatasource.markHistoryForDelete(items);
    }

    @Override
    public Completable addOrDeleteBookmark(HistoryItem historyItem) {
        return Completable.fromAction(() -> localDatasource.saveOrUpdate(historyItem));
    }

    @Override
    public Completable deleteHistory() {
        return localDatasource.deleteHistory();
    }

    private void addOrUpdate(Translate translate) {
        localDatasource.saveOrUpdate(translate.getHistoryItem());
    }

    private Observable<Translate> fromDictionarySource(String query) {
        return dictionary.getTranslate(queryFactory.createForDict(query));
    }

    private Observable<Translate> fromTranslateSource(String query) {
        return translate.getTranslate(queryFactory.createForTrans(query));
    }
}
