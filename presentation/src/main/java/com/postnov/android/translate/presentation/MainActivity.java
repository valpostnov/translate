package com.postnov.android.translate.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.postnov.android.translate.presentation.base.BaseActivity;
import com.postnov.android.translate.presentation.main.MainPagerAdapter;
import com.postnov.android.translate.presentation.main.NoSwipeViewPager;

import butterknife.BindView;

/**
 * @author Valentin Postnov
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.main_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.main_viewpager)
    NoSwipeViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
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
