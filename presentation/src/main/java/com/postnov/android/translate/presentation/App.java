package com.postnov.android.translate.presentation;

import android.app.Application;
import android.content.Context;

import com.postnov.android.translate.presentation.di.components.ApplicationComponent;
import com.postnov.android.translate.presentation.di.components.DaggerApplicationComponent;
import com.postnov.android.translate.presentation.di.modules.ApplicationModule;

/**
 * @author Valentin Postnov
 */
public class App extends Application {

    private ApplicationComponent applicationComponent;

    public static App from(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
