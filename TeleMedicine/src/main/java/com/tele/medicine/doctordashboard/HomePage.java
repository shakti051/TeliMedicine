package com.tele.medicine.doctordashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.tele.medicine.R;
import com.tele.medicine.SearchResultActivity;
import com.tele.medicine.adapterview.ViewPagerAdapter;
import com.tele.medicine.doctoradapterview.DoctorHomePagerAdapter;

/**
 * Created by Database on 7/14/2016.
 */
public class HomePage extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DoctorHomePagerAdapter viewPagerAdapter;
    private ImageView toolBarSearchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_home_page);

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

        viewPagerAdapter = new DoctorHomePagerAdapter(getSupportFragmentManager());
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
       // toolBarSearchIcon.setOnClickListener(this);
    }

    private void setUpIds()
    {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
       /* toolBarSearchIcon = (ImageView) findViewById(R.id.toolbar_searchIcon);
        toolBarSearchIcon.setVisibility(View.VISIBLE);*/

    }

   /* @Override
    public void onClick(View v)
    {
        Intent i = new Intent(HomeScreen.this, SearchResultActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
    }
    }*/
}
