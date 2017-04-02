package com.postnov.android.translate.presentation.base;

import com.postnov.android.translate.presentation.utils.RxTransformer;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Valentin Postnov
 */
public class BaseMvpPresenter<V extends BaseMvpView> {

    @Inject
    protected RxTransformer rxTransformer;

    private final CompositeSubscription compositeSubscription;

    private V view;

    public BaseMvpPresenter() {
        compositeSubscription = new CompositeSubscription();
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        compositeSubscription.clear();
        view = null;
    }

    public V getView() {
        return view;
    }

    protected <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber) {
        addSubscription(observable.compose(rxTransformer.chainSchedulers()).subscribe(subscriber));
    }

    protected <T> void subscribe(Single<T> single, SingleSubscriber<T> subscriber) {
        addSubscription(single.compose(rxTransformer.singleChainSchedulers()).subscribe(subscriber));
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}
