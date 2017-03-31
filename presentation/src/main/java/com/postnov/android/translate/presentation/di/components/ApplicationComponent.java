package com.postnov.android.translate.presentation.di.components;

import com.postnov.android.translate.presentation.di.modules.ActivityModule;
import com.postnov.android.translate.presentation.di.modules.ApplicationModule;
import com.postnov.android.translate.presentation.di.modules.TranslateModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Valentin Postnov
 */
@Singleton
@Component(modules = {ApplicationModule.class, TranslateModule.class})
public interface ApplicationComponent {

    ActivityComponent plus(ActivityModule module);
}
