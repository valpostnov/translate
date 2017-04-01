package com.postnov.android.translate.presentation.history;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.postnov.android.translate.presentation.R;
import com.postnov.android.translate.presentation.main.MainFragment;

import static com.postnov.android.translate.presentation.MainActivity.SELECTED_TAB;

/**
 * @author Valentin Postnov
 */
public class TabPagerAdapter implements TabLayout.OnTabSelectedListener {

    private final TabLayout tabLayout;
    private final SparseArray<Fragment> fragments;
    private final FragmentManager fragmentManager;

    public TabPagerAdapter(TabLayout tabLayout, FragmentManager fragmentManager, Bundle savedInstanceState) {
        this.tabLayout = tabLayout;
        this.tabLayout.addOnTabSelectedListener(this);
        this.fragmentManager = fragmentManager;
        fragments = new SparseArray<>(2);
        initFragments();
        initTabs(savedInstanceState);
    }

    @Override
    public void onTabSelected(Tab tab) {
        replaceFragment(tab.getPosition(), (String) tab.getText());
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

    private void initTabs(Bundle savedInstanceState) {
        Tab bookmarks = tabLayout.newTab().setText("Переводчик");
        Tab history = tabLayout.newTab().setText("Избранное");
        tabLayout.addTab(bookmarks);
        tabLayout.addTab(history);

        if (savedInstanceState != null) {
            tabLayout.getTabAt(savedInstanceState.getInt(SELECTED_TAB)).select();
        } else {
            bookmarks.select();
        }
    }

    private void initFragments() {
        fragments.append(0, MainFragment.newInstance());
        fragments.append(1, HistoryContainerFragment.newInstance());
    }

    private void replaceFragment(int position, String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = fragments.get(position);
        } else {
            fragments.setValueAt(position, fragment);
        }

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }
}
