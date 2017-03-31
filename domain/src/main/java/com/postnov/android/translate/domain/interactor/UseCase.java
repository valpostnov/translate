package com.postnov.android.translate.domain.interactor;

/**
 * @author Valentin Postnov
 */
public interface UseCase<REQUEST_VALUES, RESPONSE_VALUES> {

    RESPONSE_VALUES execute(REQUEST_VALUES values);
}
