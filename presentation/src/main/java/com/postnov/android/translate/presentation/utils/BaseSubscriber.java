package com.postnov.android.translate.presentation.utils;

import rx.Subscriber;

/**
 * @author Valentin Postnov
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        //empty
    }

    @Override
    public void onError(Throwable e) {
        //empty
    }

    @Override
    public void onNext(T t) {
        //empty
    }
}
