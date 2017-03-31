package com.postnov.android.translate.presentation.utils;


import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author Valentin Postnov
 */
public class BaseRxTransformer implements RxTransformer {

    @Override
    public <T> Observable.Transformer<T, T> subscribeOn() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Observable.Transformer<T, T> observeOn() {
        return tObservable -> tObservable.observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Observable.Transformer<T, T> chainSchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Single.Transformer<T, T> singleSubscribeOn() {
        return single -> single.subscribeOn(Schedulers.io());
    }

    @Override
    public <T> Single.Transformer<T, T> singleObserveOn() {
        return single -> single.observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> Single.Transformer<T, T> singleChainSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable.Transformer completableSubscribeOn() {
        return completable -> completable.subscribeOn(Schedulers.io());
    }

    @Override
    public Completable.Transformer completableObserveOn() {
        return completable -> completable.observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable.Transformer completableChainSchedulers() {
        return completable -> completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
