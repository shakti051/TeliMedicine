package com.tele.medicine.pharmacyadapterview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tele.medicine.pharmacydashboard.pharmacyhometabfragment.PharmacyHomeTabFragment;
import com.tele.medicine.pharmacydashboard.pharmacyhometabfragment.PharmacyProfileTabFragment;
import com.tele.medicine.pharmacydashboard.pharmacyhometabfragment.PharmacySettingsTabFragment;

/**
 * Created by Database on 7/28/2016.
 */
public class PharmacyHomePagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PharmacyHomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PharmacyHomeTabFragment homeTab = new PharmacyHomeTabFragment();
                return homeTab;
            case 1:
                PharmacyProfileTabFragment profileTab = new PharmacyProfileTabFragment();
                return profileTab;
            case 2:
                PharmacySettingsTabFragment menuOptionTab = new PharmacySettingsTabFragment();
                return menuOptionTab;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
