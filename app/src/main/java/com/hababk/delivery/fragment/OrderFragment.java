package com.hababk.delivery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.NewOrderAdapter;
import com.hababk.delivery.utils.pojoclasses.NewOrderDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2/1/2018.
 */

public class OrderFragment extends Fragment {
    @BindView(R.id.new_order_recycler_view)
    RecyclerView mNewOrderRv;
    private NewOrderAdapter mNewOrderAdapter;
    private ArrayList<NewOrderDetail> mNewOrderDetailList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_order, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        setNewOrderList();
        initRecyclerView();
    }

    private void setNewOrderList() {
        mNewOrderDetailList = new ArrayList<>();
        mNewOrderDetailList.add(new NewOrderDetail("Ahmed Ali", "40 BHD", "042", "11:45 am, 01 Jan,2018", "Cash", 0));
        mNewOrderDetailList.add(new NewOrderDetail("Johny Jonson", "52 BHD", "042", "11:45 am, 01 Jan,2018", "Credit Card", 1));
        mNewOrderDetailList.add(new NewOrderDetail("Helly Tonny", "40 BHD", "042", "11:45 am, 01 Jan,2018", "Cash", 1));
        mNewOrderDetailList.add(new NewOrderDetail("Tina roy", "52 BHD", "042", "11:45 am, 01 Jan,2018", "Cash", 1));
        mNewOrderDetailList.add(new NewOrderDetail("Abhijeet Sharma", "35 BHD", "042", "11:45 am, 01 Jan,2018", "Credit Card", 0));
        mNewOrderDetailList.add(new NewOrderDetail("Rakesh Kapoor", "28 BHD", "042", "11:45 am, 01 Jan,2018", "Cash", 1));
    }

    private void initRecyclerView() {
        mNewOrderAdapter = new NewOrderAdapter(getContext());
        mNewOrderAdapter.setmNewOrderDetails(mNewOrderDetailList);
        mNewOrderRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewOrderRv.setItemAnimator(new DefaultItemAnimator());
        mNewOrderRv.setAdapter(mNewOrderAdapter);
    }

}
