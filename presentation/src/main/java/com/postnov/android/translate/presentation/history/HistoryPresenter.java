package com.postnov.android.translate.presentation.history;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.interactor.AddOrDeleteBookmarkUseCase;
import com.postnov.android.translate.domain.interactor.DeleteHistoryUseCase;
import com.postnov.android.translate.domain.interactor.GetHistoryUseCase;
import com.postnov.android.translate.presentation.base.BaseMvpPresenter;
import com.postnov.android.translate.presentation.bus.RxBus;
import com.postnov.android.translate.presentation.utils.BaseSingleSubscriber;
import com.postnov.android.translate.presentation.utils.BaseSubscriber;
import com.pushtorefresh.storio.sqlite.Changes;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
class HistoryPresenter extends BaseMvpPresenter<HistoryView> {

    private final GetHistoryUseCase getHistoryUseCase;
    private final AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase;
    private final DeleteHistoryUseCase deleteHistoryUseCase;
    private final Observable<Changes> changesObservable;
    private final RxBus rxBus;

    @Inject
    HistoryPresenter(GetHistoryUseCase getHistoryUseCase, AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase,
                     DeleteHistoryUseCase deleteHistoryUseCase, Observable<Changes> changesObservable, RxBus rxBus) {
        this.getHistoryUseCase = getHistoryUseCase;
        this.addOrDeleteBookmarkUseCase = addOrDeleteBookmarkUseCase;
        this.deleteHistoryUseCase = deleteHistoryUseCase;
        this.changesObservable = changesObservable;
        this.rxBus = rxBus;
    }

    void fetchHistory() {
        subscribe(getHistoryUseCase.execute(null), new BaseSingleSubscriber<List<HistoryItem>>() {
            @Override
            public void onSuccess(List<HistoryItem> value) {
                getView().showHistory(value);
            }
        });
    }

    void addOrDeleteBookmark(HistoryItem item) {
        addSubscription(addOrDeleteBookmarkUseCase.execute(item)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe(() -> rxBus.post(item)));
    }

    void subscribeOnEvents() {
        subscribe(changesObservable, new BaseSubscriber<Changes>() {
            @Override
            public void onNext(Changes changes) {
                fetchHistory();
            }
        });
    }

    void deleteAll(List<HistoryItem> items) {
        addSubscription(deleteHistoryUseCase.execute(items)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe(() -> rxBus.post(Collections.max(items))));
    }
}
