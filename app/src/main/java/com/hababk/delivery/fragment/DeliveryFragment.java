package com.hababk.delivery.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.DeliveryListAdapter;
import com.hababk.delivery.utils.pojoclasses.DeliveryDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 1/28/2018.
 */

public class DeliveryFragment extends Fragment {
    @BindView(R.id.delivery_recycler_view)
    RecyclerView mRecyclerView;
    private DeliveryListAdapter mDeliveryListAdapter;
    private ArrayList<DeliveryDetail> mDeliveryDetailList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        setVariablesToDefaultValues();
        initRecyclerView();
    }

    private void setVariablesToDefaultValues() {
        mDeliveryDetailList = new ArrayList<>();
        mDeliveryDetailList.add(new DeliveryDetail("Perry Johnson", "Umm Amar Road, Dubai", "22 Old Golden Road, Dubai", "042", "10 mins ago", true));
        mDeliveryDetailList.add(new DeliveryDetail("David Anderson", "Umm Amar Road, Dubai", "22 Old Golden Road, Dubai", "042", "10 mins ago", false));
        mDeliveryDetailList.add(new DeliveryDetail("Jerry Smith", "Umm Amar Road, Dubai", "22 Old Golden Road, Dubai", "042", "10 mins ago", true));
        mDeliveryDetailList.add(new DeliveryDetail("Ritvi George", "Umm Amar Road, Dubai", "22 Old Golden Road, Dubai", "042", "10 mins ago", true));
    }

    private void initRecyclerView() {
        mDeliveryListAdapter = new DeliveryListAdapter(getContext());
        mDeliveryListAdapter.setDeliveryList(mDeliveryDetailList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDeliveryListAdapter);
    }
}
