package com.postnov.android.translate.data.repository;

import com.postnov.android.translate.data.datasource.local.LocalDatasource;
import com.postnov.android.translate.data.datasource.local.LocalDatasourceImpl;
import com.postnov.android.translate.data.datasource.local.table.HistoryItemTable;
import com.postnov.android.translate.data.datasource.remote.DictionaryRemoteDatasource;
import com.postnov.android.translate.data.datasource.remote.RemoteDatasource;
import com.postnov.android.translate.data.datasource.remote.TranslateRemoteDatasource;
import com.postnov.android.translate.data.util.CollectionUtils;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.QueryMapFactory;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.functions.Action1;

/**
 * RemoteDataSource для апи переводчика {@link TranslateRemoteDatasource}
 * RemoteDataSource для апи словаря {@link DictionaryRemoteDatasource}
 *
 * LocalDataSource {@link LocalDatasourceImpl} представляет из себя базу данных с одной таблицей {@link HistoryItemTable}
 * при каждом переводе слова или предложения происходит сохранение {@link HistoryItem}
 * Если мы указали, что данную запись нужно добавить в закладки, то она просто помечается как {@link HistoryItem#isBookmark}
 * и становится видна на экране закладок
 *
 * Когда пользователь нажмет удалить всю историю, то она сначала просто помечается на удаление {@link this#markHistoryForDelete(List)}
 * Полное удаление произойдет после завершения работы с приложением вызовом метода {@link this#deleteHistory()}
 * При этом удалятся только те записи, которые помечены как
 * {@link HistoryItem#isBookmark = false} и {@link HistoryItem#isHistory = false}
 *
 *
 * @author Valentin Postnov
 */
public class TranslateRepositoryImpl implements TranslateRepository {

    private final QueryMapFactory queryFactory;
    private final LocalDatasource localDatasource;
    private final Action1<Translate> saveTranslate;
    private final RemoteDatasource dictionary;
    private final RemoteDatasource translate;

    @Inject
    public TranslateRepositoryImpl(LocalDatasource localDatasource, QueryMapFactory queryFactory, RemoteDatasource dictionary, RemoteDatasource translate) {
        this.queryFactory = queryFactory;
        this.localDatasource = localDatasource;
        this.dictionary = dictionary;
        this.translate = translate;
        saveTranslate = this::addOrUpdate;
    }

    /**
     * Сначала запрос идет к апи словаря,
     * если результат пустой(пустой по сути, когда введено предложение),
     * переключаемся на апи переводчика
     *
     * @param query исходное слово или предложение
     */
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

    /**
     * Помечаем элементы в базе, что больше они не относятся к закладкам
     *
     * @param items список {@link HistoryItem}
     * @return Completable по успешному выполнению
     */
    @Override
    public Completable deleteBookmarks(List<HistoryItem> items) {
        CollectionUtils.doOnEach(items, item -> item.setBookmark(false));
        return localDatasource.deleteBookmarks(items);
    }

    /**
     * Помечаем элементы в базе, что больше они не относятся к истории
     *
     * @param items список {@link HistoryItem}
     * @return Completable по успешному выполнению
     */
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
