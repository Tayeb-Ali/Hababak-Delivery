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
import com.hababk.delivery.adapter.ApprovedItemListAdapter;
import com.hababk.delivery.utils.pojoclasses.ApprovedItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 1/31/2018.
 */

public class ApprovedItemFragment extends Fragment {
    @BindView(R.id.approved_item_recycler_view)
    RecyclerView mRecyclerView;
    private ApprovedItemListAdapter mApprovedItemListAdapter;
    private ArrayList<ApprovedItemDetail> mApprovedItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_approved_item_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        setApprovedItemList();
        initRecyclerView();
    }

    private void setApprovedItemList() {
        mApprovedItemList = new ArrayList<>();
        mApprovedItemList.add(new ApprovedItemDetail(R.drawable.background, "Red Chicken Tikka", "Non-Veg Food", "20 BD", true));
        mApprovedItemList.add(new ApprovedItemDetail(R.drawable.background, "Laizzize Falod Chicken", "Non-Veg Food", "32 BD", false));
        mApprovedItemList.add(new ApprovedItemDetail(R.drawable.background, "Pav Bhaji", "Veg Food", "15 BD", true));
        mApprovedItemList.add(new ApprovedItemDetail(R.drawable.background, "Spring Roll", "Veg Food", "10 BD", true));
    }

    private void initRecyclerView() {
        mApprovedItemListAdapter = new ApprovedItemListAdapter(getContext());
        mApprovedItemListAdapter.setApprovedItemList(mApprovedItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mApprovedItemListAdapter);
    }

}
