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
import com.hababk.delivery.network.ApiUtils;
import com.hababk.delivery.network.ChefService;
import com.hababk.delivery.network.response.EarningResponse;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarningsFragment extends Fragment {
    private TabLayout mEarningsTabs;
    private ViewPager mEarningsViewPager;

    private ChefService chefService;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private EarningTotalFragment totalEarningFragment;
    private EarningHistoryFragment historyEarningFragment;

    public EarningsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chefService = ApiUtils.getClient().create(ChefService.class);
        sharedPreferenceUtil = new SharedPreferenceUtil(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabbed_viewpager, container, false);
        mEarningsTabs = view.findViewById(R.id.tabLayout);
        mEarningsViewPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void getEarnings(int pageNo) {
        chefService.getEarnings(Helper.getApiToken(sharedPreferenceUtil), pageNo).enqueue(new Callback<EarningResponse>() {
            @Override
            public void onResponse(Call<EarningResponse> call, Response<EarningResponse> response) {
                if (response.isSuccessful()) {
                    setData(response.body());
                } else {
                    setData(null);
                }
            }

            @Override
            public void onFailure(Call<EarningResponse> call, Throwable t) {
                setData(null);
            }
        });
    }

    private void setData(EarningResponse body) {
        if (totalEarningFragment != null) totalEarningFragment.setData(body);
        if (historyEarningFragment != null) historyEarningFragment.setData(body);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        totalEarningFragment = new EarningTotalFragment();
        historyEarningFragment = EarningHistoryFragment.newInstance(new EarningFragmentInteractor() {
            @Override
            public void loadItems(int pageNo) {
                getEarnings(pageNo);
            }
        });
        adapter.addFragment(totalEarningFragment, getString(R.string.total));
        adapter.addFragment(historyEarningFragment, getString(R.string.history));
        mEarningsViewPager.setAdapter(adapter);
        mEarningsTabs.setupWithViewPager(mEarningsViewPager);
    }

    public interface EarningFragmentInteractor {
        void loadItems(int pageNo);
    }
}
