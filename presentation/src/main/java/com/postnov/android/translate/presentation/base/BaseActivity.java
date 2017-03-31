package com.postnov.android.translate.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.postnov.android.translate.presentation.App;
import com.postnov.android.translate.presentation.di.components.ActivityComponent;
import com.postnov.android.translate.presentation.di.modules.ActivityModule;

import butterknife.ButterKnife;

/**
 * @author Valentin Postnov
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        activityComponent = App.from(this).getApplicationComponent().plus(new ActivityModule(this));
        initInjection();
    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }

    protected abstract void initInjection();

    @LayoutRes
    protected abstract int getLayout();
}
