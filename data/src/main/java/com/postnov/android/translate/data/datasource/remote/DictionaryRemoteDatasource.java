package com.postnov.android.translate.data.datasource.remote;

import com.postnov.android.translate.data.RxBaseResponseMapper;
import com.postnov.android.translate.data.api.DictionaryApi;
import com.postnov.android.translate.data.api.error.ErrorBodyParser;
import com.postnov.android.translate.data.entity.DictionaryEntity;
import com.postnov.android.translate.data.entity.mapper.DictionaryEntityMapper;
import com.postnov.android.translate.domain.Translate;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Valentin Postnov
 */
public class DictionaryRemoteDatasource implements RemoteDatasource {

    private final DictionaryApi api;
    private final Func1<DictionaryEntity, Boolean> nonNullFilter;
    private final RxBaseResponseMapper<DictionaryEntity> responseMapper;

    @Inject
    public DictionaryRemoteDatasource(DictionaryApi api, RxBaseResponseMapper<DictionaryEntity> responseMapper) {
        this.api = api;
        this.responseMapper = responseMapper;
        nonNullFilter = def -> def != null;
    }

    @Override
    public Observable<Translate> getTranslate(Map<String, String> request) {
        return api.dictionary(request)
                .map(responseMapper)
                .filter(nonNullFilter)
                .map(new DictionaryEntityMapper(request));
    }
}
