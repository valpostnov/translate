package com.postnov.android.translate.presentation.history;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.interactor.AddOrDeleteBookmarkUseCase;
import com.postnov.android.translate.domain.interactor.DeleteBookmarksUseCase;
import com.postnov.android.translate.domain.interactor.GetBookmarksUseCase;
import com.postnov.android.translate.presentation.base.BaseMvpPresenter;
import com.postnov.android.translate.presentation.bus.RxBus;
import com.postnov.android.translate.presentation.utils.BaseSingleSubscriber;
import com.pushtorefresh.storio.sqlite.Changes;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
class BookmarksPresenter extends BaseMvpPresenter<HistoryView> {

    private final GetBookmarksUseCase getBookmarksUseCase;
    private final AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase;
    private final DeleteBookmarksUseCase deleteBookmarksUseCase;
    private final Observable<Changes> dbChangesObservable;
    private final RxBus rxBus;

    @Inject
    BookmarksPresenter(GetBookmarksUseCase getBookmarksUseCase, AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase, DeleteBookmarksUseCase deleteBookmarksUseCase, Observable<Changes> dbChangesObservable, RxBus rxBus) {
        this.getBookmarksUseCase = getBookmarksUseCase;
        this.addOrDeleteBookmarkUseCase = addOrDeleteBookmarkUseCase;
        this.deleteBookmarksUseCase = deleteBookmarksUseCase;
        this.dbChangesObservable = dbChangesObservable;
        this.rxBus = rxBus;
    }

    void fetchBookmarks() {
        subscribeIO(getBookmarksUseCase.execute(null), new BaseSingleSubscriber<List<HistoryItem>>() {
            @Override
            public void onSuccess(List<HistoryItem> value) {
                getView().showBookmarks(value);
            }
        });
    }

    void addOrDeleteBookmark(HistoryItem item) {
        addSubscription(addOrDeleteBookmarkUseCase.execute(item)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe(() -> rxBus.post(item)));
    }

    /**
     * подписываемся на события изменения в базе данных
     */
    void subscribeOnDBChangeEvents() {
        addSubscription(dbChangesObservable
                .compose(rxTransformer.subscribeOn())
                .subscribe(it -> fetchBookmarks()));
    }

    /**
     * очищаем список избранных
     * после шлем событие об изменение в базе в месте с последним элементом
     * это нужно чтобы помнять иконку закладки на главном экране.
     * костыль походу:)
     */
    void delete(List<HistoryItem> items) {
        addSubscription(deleteBookmarksUseCase.execute(items)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe(() -> rxBus.post(Collections.max(items))));
    }
}
