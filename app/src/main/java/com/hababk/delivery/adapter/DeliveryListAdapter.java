package com.hababk.delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.ActivityDetailActivity;
import com.hababk.delivery.utils.pojoclasses.DeliveryDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 1/30/2018.
 */

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.DeliveryListHolder> {
    private Context mContext;
    private ArrayList<DeliveryDetail> mDetailDetailList;

    public DeliveryListAdapter(Context context) {
        mContext = context;
        mDetailDetailList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DeliveryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item_layout, parent, false);
        return new DeliveryListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeliveryListHolder holder, int position) {
        holder.setData(mDetailDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDetailDetailList.size();
    }

    public void setDeliveryList(ArrayList<DeliveryDetail> deliveryDetail) {
        mDetailDetailList.clear();
        mDetailDetailList.addAll(deliveryDetail);
    }

    public class DeliveryListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.statusDelivery)
        TextView actionDelivery;

        public DeliveryListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, ActivityDetailActivity.class));
                }
            });
        }

        public void setData(DeliveryDetail deliveryDetail) {
            actionDelivery.setText(deliveryDetail.isAccepted() ? "Accepted" : "Accept");
            actionDelivery.setTextColor(ContextCompat.getColor(mContext, deliveryDetail.isAccepted() ? R.color.Lime : android.R.color.white));
            actionDelivery.setBackgroundResource(deliveryDetail.isAccepted() ? 0 : R.drawable.square_accent);
        }
    }
}
