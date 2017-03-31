package com.postnov.android.translate.presentation.utils;


import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * @author Valentin Postnov
 */
public interface RxTransformer {

    <T> Observable.Transformer<T, T> subscribeOn();

    <T> Observable.Transformer<T, T> observeOn();

    <T> Observable.Transformer<T, T> chainSchedulers();

    <T> Single.Transformer<T, T> singleSubscribeOn();

    <T> Single.Transformer<T, T> singleObserveOn();

    <T> Single.Transformer<T, T> singleChainSchedulers();

    Completable.Transformer completableSubscribeOn();

    Completable.Transformer completableObserveOn();

    Completable.Transformer completableChainSchedulers();
}
