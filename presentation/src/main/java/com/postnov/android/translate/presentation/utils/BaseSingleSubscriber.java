package com.postnov.android.translate.presentation.utils;

import rx.SingleSubscriber;

/**
 * @author Valentin Postnov
 */
public class BaseSingleSubscriber<T> extends SingleSubscriber<T> {

    @Override
    public void onSuccess(T value) {
        //empty
    }

    @Override
    public void onError(Throwable error) {
        //empty
    }
}
