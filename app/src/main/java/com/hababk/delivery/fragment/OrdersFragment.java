package com.hababk.delivery.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 1/28/2018.
 */

public class OrdersFragment extends Fragment {
    @BindView(R.id.order_tabs)
    TabLayout mOrderTabs;
    @BindView(R.id.order_viewpager)
    ViewPager mOrderViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPager();
    }

    public void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OrderFragment(), "New Orders");
        adapter.addFragment(new OrderFragment(), "Past Orders");
        mOrderViewPager.setAdapter(adapter);
        mOrderTabs.setupWithViewPager(mOrderViewPager);
        mOrderViewPager.post(new Runnable() {
            @Override
            public void run() {
                mOrderViewPager.setCurrentItem(0);
            }
        });
    }
}
