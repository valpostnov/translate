package com.postnov.android.translate.domain.interactor;

import com.postnov.android.translate.domain.repository.TranslateRepository;

import javax.inject.Inject;

import rx.Completable;

/**
 * @author Valentin Postnov
 */
public class DeleteHistoryUseCase implements UseCase<Void, Completable> {

    private final TranslateRepository translateRepository;

    @Inject
    public DeleteHistoryUseCase(TranslateRepository translateRepository) {
        this.translateRepository = translateRepository;
    }

    @Override
    public Completable execute(Void aVoid) {
        return translateRepository.deleteHistory();
    }
}
