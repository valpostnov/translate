package com.postnov.android.translate.domain.interactor;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import javax.inject.Inject;

import rx.Completable;

/**
 * @author Valentin Postnov
 */
public class AddOrDeleteBookmarkUseCase implements UseCase<HistoryItem, Completable> {

    private final TranslateRepository repository;

    @Inject
    public AddOrDeleteBookmarkUseCase(TranslateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(HistoryItem item) {
        return repository.addOrDeleteBookmark(item);
    }
}
