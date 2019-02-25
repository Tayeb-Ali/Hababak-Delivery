package com.hababk.delivery.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.ReviewAdapter;
import com.hababk.delivery.utils.pojoclasses.ReviewDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {
    @BindView(R.id.review_recycler_view)
    RecyclerView mRecyclerView;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewDetail> mReviewDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle("Reviews");
        }
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        setReviewDetailList();
        setRecyclerViewAdapter();
    }

    private void setReviewDetailList() {
        mReviewDetailList = new ArrayList<>();
        String reviewText = "Awesome food taste as well as quick delivery  services. Really appreaciate the packing and quality of food delivered by Global fusion China gate restro. Go for it!!";
        mReviewDetailList.add(new ReviewDetail("Joseph Kelly", "Street 45", 4.2, reviewText, "5 April, 17"));
        mReviewDetailList.add(new ReviewDetail("Menon Jeryy", "Zyan Road", 2.0, reviewText, "17 Feb, 17"));
        mReviewDetailList.add(new ReviewDetail("Rutvik Desai", "George Street", 4.2, reviewText, "5 April, 17"));
    }

    private void setRecyclerViewAdapter() {
        mReviewAdapter = new ReviewAdapter(this);
        mReviewAdapter.setReviewDetailList(mReviewDetailList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mReviewAdapter);
    }

}
