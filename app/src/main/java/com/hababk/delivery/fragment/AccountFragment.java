package com.hababk.delivery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.BankDetailActivity;
import com.hababk.delivery.activity.ProfileActivity;
import com.hababk.delivery.activity.SupportActivity;

/**
 * Created by user on 1/28/2018.
 */

public class AccountFragment extends Fragment implements View.OnClickListener {

    public AccountFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        view.findViewById(R.id.profile).setOnClickListener(this);
        //view.findViewById(R.id.notificationSettings).setOnClickListener(this);
        view.findViewById(R.id.bankDetails).setOnClickListener(this);
        view.findViewById(R.id.support).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile:
                startActivity(new Intent(getContext(), ProfileActivity.class));
                break;
//            case R.id.notificationSettings:
//                startActivity(new Intent(getContext(), NotificationSettingActivity.class));
//                break;
            case R.id.bankDetails:
                startActivity(new Intent(getContext(), BankDetailActivity.class));
                break;
            case R.id.support:
                startActivity(new Intent(getContext(), SupportActivity.class));
                break;
        }
    }
}
