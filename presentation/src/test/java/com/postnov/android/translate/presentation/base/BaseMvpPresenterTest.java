package com.postnov.android.translate.presentation.base;

import com.postnov.android.translate.presentation.utils.RxTransformer;
import com.postnov.android.translate.presentation.utils.TestRxTransformer;

import org.junit.Before;
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

    @Spy
    @InjectMocks
    public BaseMvpPresenter presenter;
    @Mock
    RxTransformer rxTransformer;
    @Mock
    SingleSubscriber<Any> singleSubscriber;

    @Before
    public void baseSetUp() {
        given(rxTransformer.chainSchedulers()).willReturn(new TestRxTransformer().chainSchedulers());
        given(rxTransformer.observeOn()).willReturn(new TestRxTransformer().observeOn());
        given(rxTransformer.completableSubscribeOn()).willReturn(new TestRxTransformer().completableSubscribeOn());
    }


    public void givenObservableSubscriptionWhenSubscribe() {
        presenter.subscribeIO(Observable.empty(), new TestSubscriber<>());
        verify(presenter).addSubscription(any());
    }


    public void givenSingleSubscriptionWhenSubscribe() {
        presenter.subscribeIO(Single.just(null), singleSubscriber);
        verify(presenter).addSubscription(any());
    }
}
