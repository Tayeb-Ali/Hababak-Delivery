package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.utils.pojoclasses.ReviewDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2/2/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private Context mContext;
    private ArrayList<ReviewDetail> mReviewDetailList;

    public ReviewAdapter(Context context) {
        mContext = context;
        mReviewDetailList = new ArrayList<>();
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_layout, parent, false);
        return new ReviewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.nameTv.setText(mReviewDetailList.get(position).getmPersonName());
        holder.addressTv.setText(mReviewDetailList.get(position).getmPersonAddress());
        holder.reviewTextTv.setText(mReviewDetailList.get(position).getmReview());
        holder.dateTv.setText(mReviewDetailList.get(position).getmDate());
        holder.ratingValTv.setText(mReviewDetailList.get(position).getmRating() + "");
        holder.ratingBar.setRating((float) mReviewDetailList.get(position).getmRating());
    }

    @Override
    public int getItemCount() {
        return mReviewDetailList.size();
    }

    public void setReviewDetailList(ArrayList<ReviewDetail> reviewDetailList) {
        mReviewDetailList.clear();
        mReviewDetailList.addAll(reviewDetailList);
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_person_name_tv)
        TextView nameTv;
        @BindView(R.id.review_person_address_tv)
        TextView addressTv;
        @BindView(R.id.review_text_tv)
        TextView reviewTextTv;
        @BindView(R.id.review_date_tv)
        TextView dateTv;
        @BindView(R.id.review_rating_val_tv)
        TextView ratingValTv;
        @BindView(R.id.review_ratingbar)
        RatingBar ratingBar;


        public ReviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
