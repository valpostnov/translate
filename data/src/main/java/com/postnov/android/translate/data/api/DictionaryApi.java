package com.postnov.android.translate.data.api;

import com.postnov.android.translate.data.entity.DictionaryEntity;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author Valentin Postnov
 */
public interface DictionaryApi {

    @GET("lookup")
    Observable<Response<DictionaryEntity>> dictionary(@QueryMap Map<String, String> request);
}
