package com.hababk.delivery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.AddItemActivity;
import com.hababk.delivery.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 1/28/2018.
 */

public class ItemsFragment extends Fragment {
    @BindView(R.id.item_tabs)
    TabLayout mItemTabs;
    @BindView(R.id.item_viewpager)
    ViewPager mItemViewPager;
    @BindView(R.id.item_add_fab)
    FloatingActionButton mItemAddFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPager();
    }

    public void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ApprovedItemFragment(), "Approved Items");
        adapter.addFragment(new PendingItemFragment(), "Pending Items");
        mItemViewPager.setAdapter(adapter);
        mItemTabs.setupWithViewPager(mItemViewPager);
        mItemViewPager.post(new Runnable() {
            @Override
            public void run() {
                mItemViewPager.setCurrentItem(0);
            }
        });
    }

    @OnClick({R.id.item_add_fab})
    public void onClickFabOpenAddItemActivity() {
        startActivity(new Intent(getContext(), AddItemActivity.class));
    }
}
