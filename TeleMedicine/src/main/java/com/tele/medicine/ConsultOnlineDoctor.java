package com.tele.medicine;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tele.medicine.utilities.CircularImageView;
import com.tele.medicine.utilities.HeaderView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Database on 7/20/2016.
 */
public class ConsultOnlineDoctor extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener
{
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.toolbar_header_view)
    HeaderView toolbarHeaderView;

    @Bind(R.id.float_header_view)
    HeaderView floatHeaderView;

    @Bind(R.id.profile_imageID)
    CircularImageView imageView;

    private boolean isHideToolbarView = false;
    public View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_online_doctor);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout.setTitle(" ");
        toolbarHeaderView.bindTo("Dr. Subhash Tiwari", "MD-HRM");
        floatHeaderView.bindTo("Dr. Subhash Tiwari", "MD-HRM");
        appBarLayout.addOnOffsetChangedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
