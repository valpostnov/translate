package com.postnov.android.translate.domain.interactor;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Completable;

/**
 * @author Valentin Postnov
 */
public class DeleteHistoryUseCase implements UseCase<List<HistoryItem>, Completable> {

    private final TranslateRepository repository;

    @Inject
    public DeleteHistoryUseCase(TranslateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(List<HistoryItem> items) {
        return repository.deleteHistory(items);
    }
}
