package com.hababk.delivery.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hababk.delivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 1/28/2018.
 */

public class ForgotPasswordFragment extends Fragment {
    @BindView(R.id.forgot_pass_email_tv)
    EditText mEmailTv;
    @BindView(R.id.forgot_pass_send_mail_btn)
    Button mSendMailBtn;
    @BindView(R.id.forgot_pass_login_tv)
    TextView mLoginTv;
    @BindView(R.id.forgot_pass_back_iv)
    ImageView mBackIv;
    private Context mContext;

    public ForgotPasswordFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_pass_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        mContext = getContext();
    }

    private void init() {

    }

    @OnClick({R.id.forgot_pass_back_iv, R.id.forgot_pass_login_tv})
    public void onClickBack() {
        getActivity().onBackPressed();
    }

}
