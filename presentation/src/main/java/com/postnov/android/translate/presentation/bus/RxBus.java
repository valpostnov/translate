package com.postnov.android.translate.presentation.bus;

import com.postnov.android.translate.domain.HistoryItem;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @author Valentin Postnov
 */
public class RxBus {

    private final Subject<HistoryItem, HistoryItem> bus;

    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public void post(HistoryItem o) {
        bus.onNext(o);
    }

    public Observable<HistoryItem> toObservable() {
        return bus.onBackpressureDrop();
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
