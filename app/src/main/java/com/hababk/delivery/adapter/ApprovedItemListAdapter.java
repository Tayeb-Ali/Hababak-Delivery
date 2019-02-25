package com.hababk.delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.ItemInfoActivity;
import com.hababk.delivery.utils.pojoclasses.ApprovedItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 1/31/2018.
 */

public class ApprovedItemListAdapter extends RecyclerView.Adapter<ApprovedItemListAdapter.ApprovedItemListHolder> {
    private Context mContext;
    private ArrayList<ApprovedItemDetail> mApprovedItemList;

    public ApprovedItemListAdapter(Context context) {
        mContext = context;
        mApprovedItemList = new ArrayList<>();
    }

    @Override
    public ApprovedItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.approved_item_layout, parent, false);
        return new ApprovedItemListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ApprovedItemListHolder holder, int position) {
        holder.itemNameTv.setText(mApprovedItemList.get(position).getmItemName());
        holder.itemTypeTv.setText(mApprovedItemList.get(position).getmItemType());
        holder.itemPriceTv.setText(mApprovedItemList.get(position).getmPrice());
        holder.availableStatus = mApprovedItemList.get(position).ismAvailableStatus();
        holder.itemSwitch.setChecked(holder.availableStatus);
    }

    @Override
    public int getItemCount() {
        return mApprovedItemList.size();
    }

    public void setApprovedItemList(ArrayList<ApprovedItemDetail> approvedItemList) {
        mApprovedItemList.clear();
        mApprovedItemList.addAll(approvedItemList);
    }

    public class ApprovedItemListHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.approved_item_name_tv)
        TextView itemNameTv;
        @BindView(R.id.approved_item_type_tv)
        TextView itemTypeTv;
        @BindView(R.id.approved_item_price_val_tv)
        TextView itemPriceTv;
        @BindView(R.id.approved_item_available_switch)
        SwitchCompat itemSwitch;
        @BindView(R.id.approved_item_more_iv)
        ImageView itemMoreIv;
        @BindView(R.id.approved_item_image_view)
        ImageView itemImageView;
        boolean availableStatus = false;


        public ApprovedItemListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.approved_item_layout})
        public void onClickItemLayout() {
            Intent itemInfoIntent = new Intent(mContext, ItemInfoActivity.class);
            mContext.startActivity(itemInfoIntent);
        }

    }

}