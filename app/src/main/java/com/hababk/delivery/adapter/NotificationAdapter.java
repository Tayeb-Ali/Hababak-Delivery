package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.utils.pojoclasses.NotificationData;

import java.util.ArrayList;

/**
 * Created by a_man on 19-02-2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private ArrayList<NotificationData> dataList;
    private Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "2 mins ago", false));
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "2 mins ago", false));
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "1 hour ago", true));
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "2 hour ago", true));
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "3 hour ago", true));
        this.dataList.add(new NotificationData("Global Chef", "Lorem ipsum dolor sit amet, consectetur, sed to elusmod", "4 hour ago", true));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView notificationTime, notificationTitle, notificationDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            notificationDescription = itemView.findViewById(R.id.notificationDescription);
            notificationTitle = itemView.findViewById(R.id.notificationTitle);
            notificationTime = itemView.findViewById(R.id.notificationTime);
        }

        public void setData(NotificationData notificationData) {
            notificationDescription.setText(notificationData.getDescription());
            notificationTime.setText(notificationData.getTime());
            notificationTitle.setText(notificationData.getTitle());
            notificationTime.setTextColor(ContextCompat.getColor(context, notificationData.isRead() ? R.color.colorDarkText1 : R.color.colorAccent));
        }
    }
}
