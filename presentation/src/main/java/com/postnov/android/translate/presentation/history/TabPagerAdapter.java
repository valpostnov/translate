package com.postnov.android.translate.presentation.history;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.main.MainFragment;

/**
 * @author Valentin Postnov
 */
public class TabPagerAdapter implements TabLayout.OnTabSelectedListener {

    private final TabLayout tabLayout;
    private final SparseArray<Fragment> fragments;
    private final FragmentManager fragmentManager;

    public TabPagerAdapter(TabLayout tabLayout, FragmentManager fragmentManager) {
        this.tabLayout = tabLayout;
        this.tabLayout.addOnTabSelectedListener(this);
        this.fragmentManager = fragmentManager;
        fragments = new SparseArray<>(2);
        initFragments();
        initTabs();
    }

    @Override
    public void onTabSelected(Tab tab) {
        replaceFragment(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab) {
        //empty
    }

    @Override
    public void onTabReselected(Tab tab) {
        //empty
    }

    Fragment getItem() {
        return fragments.get(tabLayout.getSelectedTabPosition());
    }

    private void initTabs() {
        Tab bookmarks = tabLayout.newTab().setText("Переводчик");
        Tab history = tabLayout.newTab().setText("Избранное");
        tabLayout.addTab(bookmarks);
        tabLayout.addTab(history);
        bookmarks.select();
    }

    private void initFragments() {
        fragments.append(0, MainFragment.newInstance());
        fragments.append(1, HistoryContainerFragment.newInstance());
    }

    private void replaceFragment(int position) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragments.get(position))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
