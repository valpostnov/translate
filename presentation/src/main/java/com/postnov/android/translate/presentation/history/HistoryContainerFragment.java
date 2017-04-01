package com.postnov.android.translate.presentation.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.base.BaseFragment;
import com.postnov.android.translate.presentation.main.HistoryPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Valentin Postnov
 */
public class HistoryContainerFragment extends BaseFragment<HistoryContainerPresenter> {

    @BindView(R.id.fragment_history_container_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.fragment_history_container_viewpager)
    ViewPager viewPager;

    private HistoryPagerAdapter historyPagerAdapter;

    public static Fragment newInstance() {
        return new HistoryContainerFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
    }

    @Override
    protected void initInjection() {
        //empty
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_history_container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @OnClick(R.id.fragment_history_container_delete_history)
    void onDeleteClick() {
        BaseHistoryFragment fragment = (BaseHistoryFragment) historyPagerAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
        if (fragment.historyItemAdapter.getItemCount() != 0) {
            fragment.onClickDelete();
        }
    }

    private void initViewPager() {
        historyPagerAdapter = new HistoryPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(historyPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
