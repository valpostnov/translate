package com.postnov.android.translate.presentation.di.modules;

import com.postnov.android.translate.data.RxBaseResponseMapper;
import com.postnov.android.translate.data.api.DictionaryApi;
import com.postnov.android.translate.data.api.TranslateApi;
import com.postnov.android.translate.data.api.error.ErrorBodyParser;
import com.postnov.android.translate.data.datasource.local.LocalDatasource;
import com.postnov.android.translate.data.datasource.local.LocalDatasourceImpl;
import com.postnov.android.translate.data.datasource.remote.DictionaryRemoteDatasource;
import com.postnov.android.translate.data.datasource.remote.RemoteDatasource;
import com.postnov.android.translate.data.datasource.remote.TranslateRemoteDatasource;
import com.postnov.android.translate.data.entity.DictionaryEntity;
import com.postnov.android.translate.data.entity.TranslateEntity;
import com.postnov.android.translate.data.repository.TranslateRepositoryImpl;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.QueryMapFactory;
import com.postnov.android.translate.domain.interactor.AddOrDeleteBookmarkUseCase;
import com.postnov.android.translate.domain.interactor.GetBookmarksUseCase;
import com.postnov.android.translate.domain.interactor.GetHistoryUseCase;
import com.postnov.android.translate.domain.interactor.GetTranslateUseCase;
import com.postnov.android.translate.domain.repository.TranslateRepository;
import com.postnov.android.translate.presentation.bus.RxBus;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author Valentin Postnov
 */
@Module
public class TranslateModule {

    private static final String DICTIONARY_ENDPOINT = "empty";
    private static final String TRANSLATE_ENDPOINT = "empty";

    @Provides
    @Singleton
    DictionaryApi provideDictionaryApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(DICTIONARY_ENDPOINT)
                .build()
                .create(DictionaryApi.class);

    }

    @Provides
    @Singleton
    TranslateRepository provideTranslateRepository(
            LocalDatasource localDatasource, @Named("dictionary") RemoteDatasource dictionaryDatasource,
            @Named("translate") RemoteDatasource translateDatasource, QueryMapFactory queryMapFactory) {

        return new TranslateRepositoryImpl(localDatasource, queryMapFactory, dictionaryDatasource, translateDatasource);
    }

    @Provides
    @Singleton
    @Named("dictionary")
    RemoteDatasource provideDictionaryRemoteDatasource(DictionaryApi api, RxBaseResponseMapper<DictionaryEntity> mapper) {
        return new DictionaryRemoteDatasource(api, mapper);
    }

    @Provides
    @Singleton
    LocalDatasource provideLocalDatasource(StorIOSQLite storIOSQLite) {
        return new LocalDatasourceImpl(storIOSQLite);
    }

    @Provides
    @Singleton
    @Named("translate")
    RemoteDatasource provideTranslateRemoteDatasource(TranslateApi api, RxBaseResponseMapper<TranslateEntity> mapper) {
        return new TranslateRemoteDatasource(api, mapper);
    }

    @Provides
    @Singleton
    TranslateApi provideTranslateApi(Retrofit.Builder builder) {
        return builder
                .baseUrl(TRANSLATE_ENDPOINT).build()
                .create(TranslateApi.class);
    }

    @Provides
    @Singleton
    GetTranslateUseCase provideGetTranslateUseCase(TranslateRepository repository) {
        return new GetTranslateUseCase(repository);
    }

    @Provides
    @Singleton
    GetHistoryUseCase provideGetHistoryUseCase(TranslateRepository repository) {
        return new GetHistoryUseCase(repository);
    }

    @Provides
    @Singleton
    GetBookmarksUseCase provideGetFavoriteUseCase(TranslateRepository repository) {
        return new GetBookmarksUseCase(repository);
    }

    @Provides
    @Singleton
    AddOrDeleteBookmarkUseCase provideAddDeleteBookmarkUseCase(TranslateRepository repository) {
        return new AddOrDeleteBookmarkUseCase(repository);
    }

    @Provides
    @Singleton
    RxBaseResponseMapper<TranslateEntity> provideTranslateBaseResponseMapper(ErrorBodyParser errorBodyParser) {
        return new RxBaseResponseMapper<>(errorBodyParser);
    }

    @Provides
    @Singleton
    RxBaseResponseMapper<DictionaryEntity> provideDictionaryBaseResponseMapper(ErrorBodyParser errorBodyParser) {
        return new RxBaseResponseMapper<>(errorBodyParser);
    }

    @Provides
    @Singleton
    Observable<HistoryItem> provideHistoryItemChangesObservable(RxBus rxBus) {
        return rxBus.toObservable();
    }
}
