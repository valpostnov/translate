package com.postnov.android.translate.presentation.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.postnov.android.translate.presentation.history.BaseHistoryFragment;
import com.postnov.android.translate.presentation.history.BookmarksFragment;
import com.postnov.android.translate.presentation.history.HistoryContainerFragment;
import com.postnov.android.translate.presentation.history.HistoryFragment;

/**
 * @author Valentin Postnov
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final String[] NAMES = {"Переводчик", "Избранное"};
    private final SparseArray<Fragment> fragments = new SparseArray<>();

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.append(0, MainFragment.newInstance());
        fragments.append(1, HistoryContainerFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.valueAt(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return NAMES[position];
    }
}
