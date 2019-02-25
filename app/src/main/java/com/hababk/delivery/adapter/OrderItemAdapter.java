package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.utils.pojoclasses.OrderItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2/5/2018.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemHolder> {


    public Context mContext;
    private ArrayList<OrderItemDetail> mOrderItemDetailList;
    private String X_SEPRATOR = " X ";
    private String PRICE_UNIT = " BD";

    public OrderItemAdapter(Context context) {
        mContext = context;
        mOrderItemDetailList = new ArrayList<>();
    }

    public OrderItemAdapter(Context context, ArrayList<OrderItemDetail> mOrderItemDetailList) {
        this.mContext = context;
        this.mOrderItemDetailList = mOrderItemDetailList;
    }

    @Override
    public OrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_layout, parent, false);
        return new OrderItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderItemHolder holder, int position) {
        holder.mNameTv.setText(mOrderItemDetailList.get(position).getmName());
        holder.mPriceQuantityTv.setText(mOrderItemDetailList.get(position).getmQuantity() +
                X_SEPRATOR + mOrderItemDetailList.get(position).getmPrice() + PRICE_UNIT);
        int subTotal = Integer.valueOf(mOrderItemDetailList.get(position).getmQuantity()) * Integer.valueOf(mOrderItemDetailList.get(position).getmPrice());
        holder.mSubTotalTv.setText(subTotal + PRICE_UNIT);
    }

    @Override
    public int getItemCount() {
        return mOrderItemDetailList.size();
    }

    public void setmOrderItemDetailList(ArrayList<OrderItemDetail> orderItemDetailList) {
        mOrderItemDetailList.clear();
        mOrderItemDetailList.addAll(orderItemDetailList);
    }

    public class OrderItemHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.order_item_name_tv)
        TextView mNameTv;
        @BindView(R.id.order_item_sub_total_tv)
        TextView mSubTotalTv;
        @BindView(R.id.order_item_price_quantity_tv)
        TextView mPriceQuantityTv;


        public OrderItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
