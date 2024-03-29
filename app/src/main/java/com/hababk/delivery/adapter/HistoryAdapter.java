package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hababk.delivery.R;
import com.hababk.delivery.network.response.Earning;
import com.hababk.delivery.utils.Helper;
import com.hababk.delivery.utils.SharedPreferenceUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by a_man on 20-02-2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Earning> dataList;
    private String PRICEUNIT;

    public HistoryAdapter(Context context, ArrayList<Earning> orderItems) {
        this.context = context;
        this.dataList = orderItems;
        String currency = Helper.getSetting(new SharedPreferenceUtil(context), "currency");
        PRICEUNIT = TextUtils.isEmpty(currency) ? "" : " " + currency;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getTag() == null ? 0 : 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, parent, false));
        else
            return new TagViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TagViewHolder extends MyViewHolder {
        private TextView tagText;

        TagViewHolder(View itemView) {
            super(itemView);
            tagText = itemView.findViewById(R.id.tag);
        }

        @Override
        public void setData(Earning historyData) {
            tagText.setText(historyData.getTag());
        }
    }

    public class HistoryViewHolder extends MyViewHolder {
        private ImageView restImage;
        private TextView storeName, orderNumber, orderDate, orderPrice, orderProfit;
        private DecimalFormat decimalFormat;

        HistoryViewHolder(View itemView) {
            super(itemView);
            decimalFormat = new DecimalFormat("###.##");
            restImage = itemView.findViewById(R.id.storeImage);
            storeName = itemView.findViewById(R.id.storeName);
//            orderProfit = itemView.findViewById(R.id.orderProfit);
            orderPrice = itemView.findViewById(R.id.orderPrice);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderNumber = itemView.findViewById(R.id.orderNumber);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, OrderDetailActivity.class));
//                }
//            });
        }

        @Override
        public void setData(Earning historyData) {
            Glide.with(context).load(historyData.getOrder().getStore().getImage_url()).apply(RequestOptions.circleCropTransform().placeholder(R.mipmap.ic_launcher)).into(restImage);
            storeName.setText(historyData.getOrder().getStore().getName());
            orderNumber.setText("Order #" + historyData.getOrder().getId());
            orderDate.setText(Helper.timeDiff(historyData.getUpdated_at()));
            orderPrice.setText(decimalFormat.format(historyData.getOrder().getTotal()) + PRICEUNIT);
            //orderProfit.setText("Profit: " + decimalFormat.format(historyData.getOrder().getTotal()) + PRICEUNIT);
        }
    }

    public abstract class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setData(Earning historyData);
    }
}
