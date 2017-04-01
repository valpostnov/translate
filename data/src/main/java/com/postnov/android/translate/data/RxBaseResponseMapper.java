package com.postnov.android.translate.data;

import com.postnov.android.translate.data.api.error.ApiErrorEntity;
import com.postnov.android.translate.data.api.error.ErrorBodyParser;

import retrofit2.Response;
import rx.functions.Func1;

/**
 * @author Valentin Postnov
 */
public class RxBaseResponseMapper<T> implements Func1<Response<T>, T> {

    private final ErrorBodyParser errorBodyParser;

    public RxBaseResponseMapper(ErrorBodyParser errorBodyParser) {
        this.errorBodyParser = errorBodyParser;
    }

    @Override
    public T call(Response<T> response) {
        if (response.isSuccessful()) {
            return response.body();
        }

        ApiErrorEntity apiErrorEntity = errorBodyParser.parseResponse(response);
        throw new IllegalStateException(errorBodyParser.errorMessage(apiErrorEntity.getCode()));
    }
}
