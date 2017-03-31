package com.postnov.android.translate.data.api;

import com.postnov.android.translate.data.entity.TranslateEntity;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Valentin Postnov
 */
public interface TranslateApi {

    @GET("translate")
    Observable<TranslateEntity> translate(@QueryMap Map<String, String> request);
}
