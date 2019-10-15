package com.tele.medicine.doctoradapterview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tele.medicine.doctordashboard.doctorhometabfragment.DoctorHomeTabFragment;
import com.tele.medicine.doctordashboard.doctorhometabfragment.DoctorProfileTabFragment;
import com.tele.medicine.doctordashboard.doctorhometabfragment.DoctorSettingsTabFragment;
import com.tele.medicine.hometabfragment.DrawerTabFragment;
import com.tele.medicine.hometabfragment.HomeTabFragment;
import com.tele.medicine.hometabfragment.ProfileTabFragment;

/**
 * Created by Database on 7/14/2016.
 */
public class DoctorHomePagerAdapter  extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public DoctorHomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DoctorHomeTabFragment homeTab = new DoctorHomeTabFragment();
                return homeTab;
            case 1:
                DoctorProfileTabFragment profileTab = new DoctorProfileTabFragment();
                return profileTab;
            case 2:
                DoctorSettingsTabFragment menuOptionTab = new DoctorSettingsTabFragment();
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
