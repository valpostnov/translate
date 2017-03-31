package com.postnov.android.translate.data;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author Valentin Postnov
 */
public class RxBaseResponseMapper<T> implements Func1<Response<T>, T> {

    @Override
    public T call(Response<T> response) {
        if (response.isSuccessful()) return response.body();

        throw new IllegalStateException("errorCode " + response.code());
    }
}
