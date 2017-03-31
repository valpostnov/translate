package com.postnov.android.translate.data.datasource.remote;

import com.postnov.android.translate.domain.Translate;

import java.util.Map;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
public interface RemoteDatasource {

    Observable<Translate> getTranslate(Map<String, String> request);
}
