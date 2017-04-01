package com.postnov.android.translate.presentation.main;

import com.postnov.android.translate.data.util.LanguageHelperImpl;
import com.postnov.android.translate.data.util.LanguageHelperImpl.LanguagePair;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.domain.interactor.AddOrDeleteBookmarkUseCase;
import com.postnov.android.translate.domain.interactor.DeleteHistoryUseCase;
import com.postnov.android.translate.domain.interactor.GetTranslateUseCase;
import com.postnov.android.translate.presentation.base.BaseMvpPresenter;
import com.postnov.android.translate.presentation.bus.RxBus;
import com.postnov.android.translate.presentation.utils.BaseSubscriber;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author Valentin Postnov
 */
public class MainPresenter extends BaseMvpPresenter<MainFragmentView> {

    private static final long DEBOUNCE_TIME = 600;

    private final GetTranslateUseCase getTranslateUseCase;
    private final AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase;
    private final Observable<HistoryItem> historyItemChangesObservable;
    private final LanguageHelperImpl languageHelper;
    private final Observable<String> queryChangeObservable;
    private final Subject<String, String> queryChangeSubject = PublishSubject.create();
    private final Action1<String> callInnerFetchTranslate;
    private final Func1<String, String> trim = String::trim;
    private final Func1<String, Boolean> isEmpty = it -> !it.isEmpty();
    private final DeleteHistoryUseCase deleteHistoryUseCase;

    @Inject
    MainPresenter(GetTranslateUseCase getTranslateUseCase, AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase,
                  RxBus bus, LanguageHelperImpl languageHelper, DeleteHistoryUseCase deleteHistoryUseCase) {
        this.getTranslateUseCase = getTranslateUseCase;
        this.addOrDeleteBookmarkUseCase = addOrDeleteBookmarkUseCase;
        this.historyItemChangesObservable = bus.toObservable();
        this.languageHelper = languageHelper;
        this.deleteHistoryUseCase = deleteHistoryUseCase;
        this.queryChangeObservable = queryChangeSubject.asObservable();
        this.callInnerFetchTranslate = this::innerFetchTranslate;
    }

    @Override
    public void attachView(MainFragmentView view) {
        super.attachView(view);
        subscribeOnEvents();
        subscribeOnQueryChange();
    }

    @Override
    public void detachView() {
        deleteHistory();
        super.detachView();
    }

    private void deleteHistory() {
        addSubscription(deleteHistoryUseCase.execute(null)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe());
    }

    void fetchTranslate(String query) {
        queryChangeSubject.onNext(query);
    }

    void fetchLanguages() {
        subscribe(languageHelper.getPair(), new BaseSubscriber<LanguagePair>() {
            @Override
            public void onNext(LanguagePair languagePair) {
                getView().setDefLanguages(languagePair.getOriginal(), languagePair.getTranslate());
            }
        });
    }

    void swapLanguages() {
        languageHelper.swapLang();
        fetchLanguages();
    }

    void addOrDeleteBookmarkForLatItem(HistoryItem item) {
        addSubscription(addOrDeleteBookmarkUseCase.execute(item)
                .compose(rxTransformer.completableSubscribeOn())
                .subscribe());
    }

    private void subscribeOnQueryChange() {
        addSubscription(queryChangeObservable
                .debounce(DEBOUNCE_TIME, MILLISECONDS)
                .map(trim)
                .filter(isEmpty)
                .compose(rxTransformer.observeOn())
                .subscribe(callInnerFetchTranslate));
    }

    private void innerFetchTranslate(String query) {
        getView().showProgress();
        subscribe(getTranslateUseCase.execute(query), new BaseSubscriber<Translate>() {
            @Override
            public void onError(Throwable e) {
                getView().hideProgress();
                getView().showError(e.getMessage());
            }

            @Override
            public void onNext(Translate translate) {
                getView().hideProgress();
                getView().showTranslate(translate);
            }
        });
    }

    private void subscribeOnEvents() {
        subscribe(historyItemChangesObservable.ofType(HistoryItem.class), new BaseSubscriber<HistoryItem>() {
            @Override
            public void onNext(HistoryItem historyItem) {
                getView().updateHistoryItem(historyItem);
            }
        });
    }
}
