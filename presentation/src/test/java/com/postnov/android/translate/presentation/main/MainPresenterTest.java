package com.postnov.android.translate.presentation.main;

import com.postnov.android.translate.data.util.LanguageHelper;
import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.interactor.AddOrDeleteBookmarkUseCase;
import com.postnov.android.translate.domain.interactor.DeleteHistoryUseCase;
import com.postnov.android.translate.domain.interactor.GetTranslateUseCase;
import com.postnov.android.translate.presentation.base.BaseMvpPresenterTest;
import com.postnov.android.translate.presentation.bus.RxBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Completable;
import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Valentin Postnov
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest extends BaseMvpPresenterTest{

    @Mock
    GetTranslateUseCase getTranslateUseCase;
    @Mock
    AddOrDeleteBookmarkUseCase addOrDeleteBookmarkUseCase;
    @Mock
    RxBus bus;
    @Mock
    LanguageHelper languageHelper;
    @Mock
    DeleteHistoryUseCase deleteHistoryUseCase;
    @Mock
    Observable<HistoryItem> historyItemChangesObservable;
    @Mock
    MainFragmentView view;

    @Spy
    @InjectMocks
    MainPresenter mainPresenter;

    @Before
    public void setUp() {
        when(deleteHistoryUseCase.execute(any())).thenReturn(Completable.complete());
        //doReturn(view).when(mainPresenter).getView();
    }

    @Test
    public void attachView() {
        mainPresenter.attachView(any());
        verify(mainPresenter).subscribeOnEvents();
        verify(mainPresenter).subscribeOnQueryChange();
    }

    @Test
    public void detachView() {
        mainPresenter.detachView();
        verify(mainPresenter).deleteHistory();
    }
}
