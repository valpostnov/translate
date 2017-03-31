package com.postnov.android.translate.presentation.di.components;

import com.postnov.android.translate.presentation.MainActivity;
import com.postnov.android.translate.presentation.di.modules.ActivityModule;
import com.postnov.android.translate.presentation.di.scope.ActivityScope;
import com.postnov.android.translate.presentation.history.BookmarksFragment;
import com.postnov.android.translate.presentation.history.HistoryFragment;
import com.postnov.android.translate.presentation.language.LanguageActivity;
import com.postnov.android.translate.presentation.main.MainFragment;

import dagger.Subcomponent;

/**
 * @author Valentin Postnov
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LanguageActivity activity);

    void inject(MainFragment fragment);

    void inject(HistoryFragment fragment);

    void inject(BookmarksFragment fragment);
}
