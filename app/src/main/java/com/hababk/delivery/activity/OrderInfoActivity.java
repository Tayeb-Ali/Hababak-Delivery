package com.hababk.delivery.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.OrderItemAdapter;
import com.hababk.delivery.utils.pojoclasses.OrderItemDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends AppCompatActivity {
    @BindView(R.id.order_info_items_recyclerview)
    RecyclerView mItemRv;
    @BindView(R.id.order_info_cancel_order_tv)
    TextView mCancelOrderTv;
    @BindView(R.id.order_info_accept_order_tv)
    TextView mAcceptOrderTv;
    @BindView(R.id.order_info_name_tv)
    TextView mNameTv;
    @BindView(R.id.order_info_payment_method2_tv)
    TextView mPaymentMethod2Tv;
    @BindView(R.id.order_info_address_tv)
    TextView mAddressTv;
    @BindView(R.id.order_info_home_iv)
    ImageView mHomeIv;
    private OrderItemAdapter mOrderItemAdapter;
    private ArrayList<OrderItemDetail> mOrderItemDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle("Order info");
        }
        findViewById(R.id.new_order_status_tv).setVisibility(View.GONE);

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
        initVariables();
    }

    private void initVariables() {
        mOrderItemDetailList = new ArrayList<>();
        setOrderItemDetailList();
        initRecyclerView();
    }

    private void setOrderItemDetailList() {
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "1"));
        mOrderItemDetailList.add(new OrderItemDetail("Paneer Khurchan", "10", "2"));
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "1"));
        //mOrderItemDetailList.add(new OrderItemDetail("Paneer Khurchan","10","2"));
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "2"));
    }

    private void initRecyclerView() {
        mOrderItemAdapter = new OrderItemAdapter(this);
        mOrderItemAdapter.setmOrderItemDetailList(mOrderItemDetailList);
        mItemRv.setLayoutManager(new LinearLayoutManager(this));
        mItemRv.setItemAnimator(new DefaultItemAnimator());
        mItemRv.setAdapter(mOrderItemAdapter);
    }

    @OnClick({R.id.order_info_open_map_layout})
    public void onClickOrderDetailMap() {
//        Intent intent = new Intent(this, DeliveryInfoActivity.class);
//        startActivity(intent);
    }

}
