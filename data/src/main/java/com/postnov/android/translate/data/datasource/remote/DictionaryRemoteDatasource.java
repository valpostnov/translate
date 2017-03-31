package com.postnov.android.translate.data.datasource.remote;

import com.postnov.android.translate.data.api.DictionaryApi;
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

    @Inject
    public DictionaryRemoteDatasource(DictionaryApi api) {
        this.api = api;
        nonNullFilter = def -> def != null;
    }

    @Override
    public Observable<Translate> getTranslate(Map<String, String> request) {
        return api.dictionary(request)
                .filter(nonNullFilter)
                .map(new DictionaryEntityMapper(request));
    }
}
