package com.tele.medicine.adapterview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tele.medicine.hometabfragment.DrawerTabFragment;
import com.tele.medicine.hometabfragment.HomeTabFragment;
import com.tele.medicine.hometabfragment.ProfileTabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Database on 6/29/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

   private int mNumOfTabs;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeTabFragment homeTab = new HomeTabFragment();
                return homeTab;
            case 1:
                ProfileTabFragment profileTab = new ProfileTabFragment();
                return profileTab;
            case 2:
                DrawerTabFragment menuOptionTab = new DrawerTabFragment();
                return menuOptionTab;
            default:
                return null;
        }
      /* if (position==0) {
           return new HomeTabFragment();
       }else if (position==1)
       {
           return new ProfileTabFragment();
       }else if (position==2)
       {
           return new DrawerTabFragment();
       }
        return null;*/
    }

    @Override
    public int getCount() {
        return 3;
    }
}

