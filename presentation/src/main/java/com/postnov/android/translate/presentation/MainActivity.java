package com.postnov.android.translate.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.postnov.android.translate.presentation.base.BaseActivity;
import com.postnov.android.translate.presentation.history.TabPagerAdapter;

import butterknife.BindView;

/**
 * @author Valentin Postnov
 */
public class MainActivity extends BaseActivity {

    public static final String SELECTED_TAB = "SELECTED_TAB";

    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_TAB, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TabPagerAdapter(tabLayout, getSupportFragmentManager(), savedInstanceState);
    }

    @Override
    protected void initInjection() {
        getComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
