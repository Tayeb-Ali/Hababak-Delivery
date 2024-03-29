package com.hababk.delivery.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.network.response.EarningResponse;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import java.text.DecimalFormat;

/**
 * Created by user on 2/3/2018.
 */

public class EarningTotalFragment extends Fragment {
    private TextView date, total;
    private ProgressBar progressBar;
    private DecimalFormat decimalFormat;
    private String PRICEUNIT;

    public EarningTotalFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        decimalFormat = new DecimalFormat("###.##");
        String currency = Helper.getSetting(new SharedPreferenceUtil(getContext()), "currency");
        PRICEUNIT = TextUtils.isEmpty(currency) ? "" : " " + currency;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_earnings, container, false);
        date = view.findViewById(R.id.date);
        total = view.findViewById(R.id.total);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    public void setData(EarningResponse body) {
        if (date != null && total != null) {
            progressBar.setVisibility(View.INVISIBLE);
            date.setText(body != null && body.getLast_earning_date() != null ? body.getLast_earning_date() + PRICEUNIT : getString(R.string.no_earning));
            total.setText(body != null ? decimalFormat.format(body.getTotal_earnings()) + PRICEUNIT : "-- --");
        }
    }
}
