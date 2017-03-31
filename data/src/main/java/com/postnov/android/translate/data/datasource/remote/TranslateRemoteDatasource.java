package com.postnov.android.translate.data.datasource.remote;

import com.postnov.android.translate.data.api.TranslateApi;
import com.postnov.android.translate.data.entity.mapper.TranslateEntityMapper;
import com.postnov.android.translate.domain.Translate;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
public class TranslateRemoteDatasource implements RemoteDatasource {

    private final TranslateApi api;

    @Inject
    public TranslateRemoteDatasource(TranslateApi api) {
        this.api = api;
    }

    @Override
    public Observable<Translate> getTranslate(Map<String, String> request) {
        return api.translate(request).map(new TranslateEntityMapper(request));
    }
}
