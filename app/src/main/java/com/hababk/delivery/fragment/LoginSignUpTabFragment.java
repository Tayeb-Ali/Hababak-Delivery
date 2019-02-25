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

public class LoginSignUpTabFragment extends Fragment {
    @BindView(R.id.auth_tabs)
    TabLayout mAuthTabs;
    @BindView(R.id.auth_viewpager)
    ViewPager mAuhViewPager;
    private int tabPosition;

    public LoginSignUpTabFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_signup_tab_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViewPager();
        mAuthTabs.setupWithViewPager(mAuhViewPager);
    }

    public void setUpViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new SignInFragment(), "Sign in");
        adapter.addFragment(new RegisterFragment(), "Register");
        mAuhViewPager.setAdapter(adapter);
        mAuhViewPager.post(new Runnable() {
            @Override
            public void run() {
                mAuhViewPager.setCurrentItem(tabPosition);
            }
        });
    }

    public static LoginSignUpTabFragment newInstance(int tabPosition) {
        LoginSignUpTabFragment fragment = new LoginSignUpTabFragment();
        fragment.tabPosition = tabPosition;
        return fragment;
    }
}