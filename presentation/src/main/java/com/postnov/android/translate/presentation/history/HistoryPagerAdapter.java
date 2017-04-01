package com.postnov.android.translate.presentation.history;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.postnov.android.translate.presentation.history.BaseHistoryFragment;
import com.postnov.android.translate.presentation.history.BookmarksFragment;
import com.postnov.android.translate.presentation.history.HistoryFragment;

/**
 * @author Valentin Postnov
 */
public class HistoryPagerAdapter extends FragmentPagerAdapter {

    private static final String[] NAMES = {"Избранное", "История"};
    private final SparseArray<BaseHistoryFragment> fragments = new SparseArray<>();

    public HistoryPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.append(0, BookmarksFragment.newInstance());
        fragments.append(1, HistoryFragment.newInstance());
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
