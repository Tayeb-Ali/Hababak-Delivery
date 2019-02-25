package com.hababk.delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.ItemInfoActivity;
import com.hababk.delivery.utils.pojoclasses.PendingItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 1/31/2018.
 */

public class PendingItemListAdapter extends RecyclerView.Adapter<PendingItemListAdapter.PendingItemListHolder> {


    private Context mContext;
    private ArrayList<PendingItemDetail> mPendingItemList;

    public PendingItemListAdapter(Context context) {
        mContext=context;
        mPendingItemList=new ArrayList<>();
    }

    @Override
    public PendingItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_item_layout,parent,false);
        return new PendingItemListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PendingItemListHolder holder, int position) {
        holder.itemNameTv.setText(mPendingItemList.get(position).getmItemName());
        holder.itemTypeTv.setText(mPendingItemList.get(position).getmItemType());
        holder.itemImageView.setImageResource(mPendingItemList.get(position).getmItemImageId());
        holder.itemStatusFlag=mPendingItemList.get(position).getmItemStatusFlag();
        switch(holder.itemStatusFlag) {
            case 0:
                holder.itemStatusTv.setText("Rejected");
                holder.itemStatusTv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                break;
            case 1:
                holder.itemStatusTv.setText("Pending");
                holder.itemStatusTv.setTextColor(mContext.getResources().getColor(R.color.Lime));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mPendingItemList.size();
    }

    public void setPendingItemList(ArrayList<PendingItemDetail> pendingItemList) {
        mPendingItemList.clear();
        mPendingItemList.addAll(pendingItemList);
    }

    public class PendingItemListHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.pending_item_name_tv)
        TextView itemNameTv;
        @BindView(R.id.pending_item_type_tv)
        TextView itemTypeTv;
        @BindView(R.id.pending_item_status_val_tv)
        TextView itemStatusTv;
        @BindView(R.id.pending_item_more_iv)
        ImageView itemMoreIv;
        @BindView(R.id.pending_item_image_view)
        ImageView itemImageView;
        int itemStatusFlag=0;

        public PendingItemListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick({R.id.pending_item_layout})
        public void onClickItemLayout() {
            Intent itemInfoIntent=new Intent(mContext, ItemInfoActivity.class);
            mContext.startActivity(itemInfoIntent);
        }

    }
}
