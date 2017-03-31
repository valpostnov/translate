package com.postnov.android.translate.presentation.base;

import com.postnov.android.translate.presentation.utils.RxTransformer;
import com.postnov.android.translate.presentation.utils.TestRxTransformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.observers.TestSubscriber;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Valentin Postnov
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseMvpPresenterTest {

    @Mock
    RxTransformer rxTransformer;

    @Mock
    SingleSubscriber<Any> singleSubscriber;

    @Spy
    @InjectMocks
    BaseMvpPresenter presenter;

    @Before
    public void setUp() {
        given(rxTransformer.chainSchedulers()).willReturn(new TestRxTransformer().chainSchedulers());
        given(rxTransformer.singleChainSchedulers()).willReturn(new TestRxTransformer().singleChainSchedulers());
    }

    @Test
    public void givenObservableSubscriptionWhenSubscribe() {
        presenter.subscribe(Observable.empty(), new TestSubscriber<>());
        verify(presenter).addSubscription(any());
    }

    @Test
    public void givenSingleSubscriptionWhenSubscribe() {
        presenter.subscribe(Single.just(null), singleSubscriber);
        verify(presenter).addSubscription(any());
    }
}
