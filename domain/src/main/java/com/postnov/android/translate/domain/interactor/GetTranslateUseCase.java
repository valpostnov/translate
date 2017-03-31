package com.postnov.android.translate.domain.interactor;

import com.postnov.android.translate.domain.Translate;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import rx.Observable;

/**
 * @author Valentin Postnov
 */
public class GetTranslateUseCase implements UseCase<String, Observable<Translate>> {

    private final TranslateRepository repository;

    public GetTranslateUseCase(TranslateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Translate> execute(String text) {
        return repository.getTranslate(text);
    }
}
