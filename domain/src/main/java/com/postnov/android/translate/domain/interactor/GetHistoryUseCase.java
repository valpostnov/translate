package com.postnov.android.translate.domain.interactor;

import com.postnov.android.translate.domain.HistoryItem;
import com.postnov.android.translate.domain.repository.TranslateRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Single;

/**
 * @author Valentin Postnov
 */
public class GetHistoryUseCase implements UseCase<Void, Single<List<HistoryItem>>> {

    private final TranslateRepository repository;

    @Inject
    public GetHistoryUseCase(TranslateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<HistoryItem>> execute(Void value) {
        return repository.getHistory();
    }
}
