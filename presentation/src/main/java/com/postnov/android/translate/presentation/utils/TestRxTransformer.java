package com.postnov.android.translate.presentation.utils;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;

/**
 * @author Valentin Postnov
 */
public class TestRxTransformer implements RxTransformer {

    @Override
    public <T> Observable.Transformer<T, T> subscribeOn() {
        return tObservable -> tObservable.subscribeOn(Schedulers.test());
    }

    @Override
    public <T> Observable.Transformer<T, T> observeOn() {
        return tObservable -> tObservable.observeOn(Schedulers.test());
    }

    @Override
    public <T> Observable.Transformer<T, T> chainSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.test())
                .observeOn(Schedulers.test());
    }

    @Override
    public <T> Single.Transformer<T, T> singleSubscribeOn() {
        return single -> single.subscribeOn(Schedulers.test());
    }

    @Override
    public <T> Single.Transformer<T, T> singleObserveOn() {
        return single -> single.observeOn(Schedulers.test());
    }

    @Override
    public <T> Single.Transformer<T, T> singleChainSchedulers() {
        return single -> single.subscribeOn(Schedulers.test())
                .observeOn(Schedulers.test());
    }

    @Override
    public Completable.Transformer completableSubscribeOn() {
        return completable -> completable.subscribeOn(Schedulers.test());
    }

    @Override
    public Completable.Transformer completableObserveOn() {
        return completable -> completable.observeOn(Schedulers.test());
    }

    @Override
    public Completable.Transformer completableChainSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.test())
                .observeOn(Schedulers.test());
    }
}
