package com.postnov.android.translate.presentation.base;

import com.postnov.android.translate.data.repository.TranslateRepositoryTest;
import com.postnov.android.translate.presentation.utils.RxTransformer;
import com.postnov.android.translate.presentation.utils.TestRxTransformer;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import rx.SingleSubscriber;

import static org.mockito.BDDMockito.given;

/**
 * такие дела
 * это базовая штука для написания тестов на презентеры,
 * но как мы видим тестов оных нет
 *
 * Но можно загялнуть сюда {@link TranslateRepositoryTest}
 * единственный тест :(
 *
 * @author Valentin Postnov
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseMvpPresenterTest {

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
        given(rxTransformer.singleChainSchedulers()).willReturn(new TestRxTransformer().singleChainSchedulers());
        given(rxTransformer.observeOn()).willReturn(new TestRxTransformer().observeOn());
        given(rxTransformer.completableSubscribeOn()).willReturn(new TestRxTransformer().completableSubscribeOn());
    }
}
