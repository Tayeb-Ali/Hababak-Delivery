package com.hababk.delivery.fragment;

import android.content.Context;
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
import com.hababk.delivery.adapter.PendingItemListAdapter;
import com.hababk.delivery.utils.pojoclasses.PendingItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 1/31/2018.
 */

public class PendingItemFragment extends Fragment {

    @BindView(R.id.pending_item_recycler_view)
    RecyclerView mRecyclerView;
    private Context mContext;
    private PendingItemListAdapter mPendingItemListAdapter;
    private ArrayList<PendingItemDetail> mPendingItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending_item_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        initVariables();
        setPendingItemList();
        initRecyclerView();
    }

    private void setPendingItemList() {
        mPendingItemList.add(new PendingItemDetail(R.drawable.background, "Shahi Paneer", "Veg Food", 1));
        mPendingItemList.add(new PendingItemDetail(R.drawable.background, "Dal Makhni", "Veg Food", 0));
        mPendingItemList.add(new PendingItemDetail(R.drawable.background, "Mix Veg", "Veg Food", 1));
        mPendingItemList.add(new PendingItemDetail(R.drawable.background, "Malai Kofta", "Veg Food", 0));
    }

    private void initRecyclerView() {
        mPendingItemListAdapter = new PendingItemListAdapter(mContext);
        mPendingItemListAdapter.setPendingItemList(mPendingItemList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mPendingItemListAdapter);
    }

    private void initVariables() {
        mContext = getContext();
        mPendingItemList = new ArrayList<>();
    }
}
