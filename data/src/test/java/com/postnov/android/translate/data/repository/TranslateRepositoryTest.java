package com.postnov.android.translate.data.repository;

import com.postnov.android.translate.data.datasource.local.LocalDatasource;
import com.postnov.android.translate.data.datasource.remote.DictionaryRemoteDatasource;
import com.postnov.android.translate.data.datasource.remote.TranslateRemoteDatasource;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.QueryMapFactory;
import com.postnov.android.translate.domain.Translate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Single;
import rx.observers.TestSubscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Valentin Postnov
 */
@RunWith(MockitoJUnitRunner.class)
public class TranslateRepositoryTest {

    @Mock
    QueryMapFactory queryFactory;
    @Mock
    LocalDatasource localDatasource;
    @Mock
    DictionaryRemoteDatasource dictionaryRemoteDatasource;
    @Mock
    TranslateRemoteDatasource translateRemoteDatasource;

    @Spy
    @InjectMocks
    TranslateRepositoryImpl translateRepository;

    private TestSubscriber<List<HistoryItem>> itemsSubscriber = new TestSubscriber<>();
    private Translate translateEntity = new Translate.Builder().build();

    @Before
    public void setUp() {
        when(queryFactory.createForDict(anyString())).thenReturn(Collections.emptyMap());
        when(queryFactory.createForTrans(anyString())).thenReturn(Collections.emptyMap());
    }

    @Test
    public void getTranslateFromDictionaryDataSource() {
        when(dictionaryRemoteDatasource.getTranslate(any())).thenReturn(Observable.just(translateEntity));
        when(translateRemoteDatasource.getTranslate(any())).thenReturn(Observable.empty());

        TestSubscriber<Translate> subscriber = new TestSubscriber<>();
        translateRepository.getTranslate("").subscribe(subscriber);
        subscriber.assertNoErrors();

        verify(localDatasource).saveOrUpdate(any());
    }

    @Test
    public void getTranslateFromTranslateDataSource() {
        when(dictionaryRemoteDatasource.getTranslate(any())).thenReturn(Observable.empty());
        when(translateRemoteDatasource.getTranslate(any())).thenReturn(Observable.just(translateEntity));

        TestSubscriber<Translate> subscriber = new TestSubscriber<>();
        translateRepository.getTranslate("").subscribe(subscriber);
        subscriber.assertNoErrors();

        verify(localDatasource).saveOrUpdate(any());
    }

    @Test
    public void getHistory() {
        when(translateRepository.getHistory()).thenReturn(Single.just(Collections.emptyList()));
        translateRepository.getHistory().subscribe(itemsSubscriber);
        itemsSubscriber.assertCompleted();
        verify(localDatasource).getHistory();
    }

    @Test
    public void getFavorite() {
        when(translateRepository.getFavorite()).thenReturn(Single.just(Collections.emptyList()));
        translateRepository.getFavorite().subscribe(itemsSubscriber);
        itemsSubscriber.assertCompleted();
        verify(localDatasource).getFavorite();
    }

    @Test
    public void addOrDeleteBookmark() {
        TestSubscriber<HistoryItem> subscriber = new TestSubscriber<>();
        translateRepository.addOrDeleteBookmark(any()).subscribe(subscriber);
        subscriber.assertCompleted();
        verify(localDatasource).saveOrUpdate(any());
    }
}
