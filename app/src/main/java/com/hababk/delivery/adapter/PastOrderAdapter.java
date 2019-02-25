package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2/1/2018.
 */

public class PastOrderAdapter extends RecyclerView.Adapter<PastOrderAdapter.PastOrderHolder> {


    private Context mContext;

    public PastOrderAdapter(Context context) {
        mContext=context;
    }

    @Override
    public PastOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PastOrderHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PastOrderHolder extends RecyclerView.ViewHolder {
        public PastOrderHolder(View itemView) {
            super(itemView);
        }
    }
}
