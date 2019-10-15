package com.tele.medicine.pharmacydashboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tele.medicine.R;
import com.tele.medicine.pharmacyadapterview.PharmacyHomePagerAdapter;

/**
 * Created by Database on 7/28/2016.
 */
public class PharmacyHomePage extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    PharmacyHomePagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacy_home_page);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        setUpIds();
        final TabLayout.Tab home = tabLayout.newTab().setIcon(R.drawable.home_tab_blue);
        final TabLayout.Tab userProfile = tabLayout.newTab().setIcon(R.drawable.my_account_tab_grey);
        final TabLayout.Tab menuOptions = tabLayout.newTab().setIcon(R.drawable.menu_tab_grey);

        tabLayout.addTab(home, 0);
        tabLayout.addTab(userProfile, 1);
        tabLayout.addTab(menuOptions, 2);

        viewPagerAdapter = new PharmacyHomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle("Home");
                        home.setIcon(R.drawable.home_tab_blue);
                        userProfile.setIcon(R.drawable.my_account_tab_grey);
                        menuOptions.setIcon(R.drawable.menu_tab_grey);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle("My Account");
                        home.setIcon(R.drawable.home_tab_grey);
                        userProfile.setIcon(R.drawable.my_account_tab_blue);
                        menuOptions.setIcon(R.drawable.menu_tab_grey);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle("Settings");
                        home.setIcon(R.drawable.home_tab_grey);
                        userProfile.setIcon(R.drawable.my_account_tab_grey);
                        menuOptions.setIcon(R.drawable.menu_tab_blue);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle("Home");
                        home.setIcon(R.drawable.home_tab_blue);
                        userProfile.setIcon(R.drawable.my_account_tab_grey);
                        menuOptions.setIcon(R.drawable.menu_tab_grey);
                        break;
                    case 1:
                        toolbar.setTitle("My Account");
                        home.setIcon(R.drawable.home_tab_grey);
                        userProfile.setIcon(R.drawable.my_account_tab_blue);
                        menuOptions.setIcon(R.drawable.menu_tab_grey);
                        break;
                    case 2:
                        toolbar.setTitle("Settings");
                        home.setIcon(R.drawable.home_tab_grey);
                        userProfile.setIcon(R.drawable.my_account_tab_grey);
                        menuOptions.setIcon(R.drawable.menu_tab_blue);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpIds()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }
}
