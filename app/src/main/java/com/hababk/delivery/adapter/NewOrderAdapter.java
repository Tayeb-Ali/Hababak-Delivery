package com.hababk.delivery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.activity.OrderInfoActivity;
import com.hababk.delivery.utils.pojoclasses.NewOrderDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2/1/2018.
 */

public class NewOrderAdapter extends RecyclerView.Adapter<NewOrderAdapter.NewOrderHolder> {
    private Context mContext;
    private ArrayList<NewOrderDetail> mNewOrderDetails;

    public NewOrderAdapter(Context context) {
        mContext = context;
        mNewOrderDetails = new ArrayList<>();
    }

    @Override
    public NewOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_order_item_layout, parent, false);
        return new NewOrderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewOrderHolder holder, int position) {
        holder.personNameTv.setText(mNewOrderDetails.get(position).getmPersonName());
        holder.priceTv.setText(mNewOrderDetails.get(position).getmPrice());
        holder.paymentMethodTv.setText(mNewOrderDetails.get(position).getmPaymentMethod());
        holder.orderNumberTv.setText("Order #" + mNewOrderDetails.get(position).getmOrderNumber());
        holder.orderDateTv.setText("Ordered on " + mNewOrderDetails.get(position).getmOrderDate());
        holder.orderStatusFlag = mNewOrderDetails.get(position).getmOrderStatusFlag();
        switch (holder.orderStatusFlag) {
            case 0:
                holder.orderStatusTv.setText("View Order");
                holder.orderStatusTv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                holder.orderStatusTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_right_accent_24dp, 0);
                break;
            case 1:
                holder.orderStatusTv.setText("Accepted");
                holder.orderStatusTv.setTextColor(mContext.getResources().getColor(R.color.Lime));
                holder.orderStatusTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_right_primary_24dp, 0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mNewOrderDetails.size();
    }

    public void setmNewOrderDetails(ArrayList<NewOrderDetail> newOrderDetails) {
        mNewOrderDetails.clear();
        mNewOrderDetails.addAll(newOrderDetails);
    }

    public class NewOrderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.new_order_person_name_tv)
        TextView personNameTv;
        @BindView(R.id.new_order_price_tv)
        TextView priceTv;
        @BindView(R.id.new_order_payment_method_tv)
        TextView paymentMethodTv;
        @BindView(R.id.new_order_status_tv)
        TextView orderStatusTv;
        @BindView(R.id.new_order_item_layout)
        LinearLayout mItemLayout;
        @BindView(R.id.new_order_order_text_tv)
        TextView orderNumberTv;
        @BindView(R.id.new_order_dispatch_text_tv)
        TextView orderDateTv;

        int orderStatusFlag = 0;

        public NewOrderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.new_order_item_layout})
        public void onClickItemLayout() {
            Intent intent = new Intent(mContext, OrderInfoActivity.class);
            mContext.startActivity(intent);
        }
    }
}
