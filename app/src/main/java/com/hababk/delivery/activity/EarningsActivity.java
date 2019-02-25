package com.hababk.delivery.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.ViewPagerAdapter;
import com.hababk.delivery.fragment.EarningTotalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarningsActivity extends AppCompatActivity {
    @BindView(R.id.earnings_tabs)
    TabLayout mEarningsTabs;
    @BindView(R.id.earnings_viewpager)
    ViewPager mEarningsViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle(R.string.earnings);
        }
        ButterKnife.bind(this);
        setUpViewPager();
        mEarningsTabs.setupWithViewPager(mEarningsViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new EarningTotalFragment(), getString(R.string.today));
        adapter.addFragment(new EarningTotalFragment(), getString(R.string.weekly));
        mEarningsViewPager.setAdapter(adapter);
        mEarningsViewPager.post(new Runnable() {
            @Override
            public void run() {
                mEarningsViewPager.setCurrentItem(0);
            }
        });
    }

}
