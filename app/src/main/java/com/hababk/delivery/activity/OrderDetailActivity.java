package com.hababk.delivery.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.adapter.OrderItemAdapter;
import com.hababk.delivery.utils.pojoclasses.OrderItemDetail;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerOrderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initUi();
        setupOrderItemRecycler();
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

    private void setupOrderItemRecycler() {
        ArrayList<OrderItemDetail> mOrderItemDetailList = new ArrayList<>();
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "1"));
        mOrderItemDetailList.add(new OrderItemDetail("Paneer Khurchan", "10", "2"));
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "1"));
        //mOrderItemDetailList.add(new OrderItemDetail("Paneer Khurchan","10","2"));
        mOrderItemDetailList.add(new OrderItemDetail("Ginger Chicken Curry", "20", "2"));
        recyclerOrderItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrderItems.setAdapter(new OrderItemAdapter(this, mOrderItemDetailList));
    }

    private void initUi() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_left_white);
            actionBar.setTitle("Order #042");
        }
        recyclerOrderItems = findViewById(R.id.recyclerOrderItems);
        ((TextView) findViewById(R.id.actionDelivery)).setText("Paid");
    }
}
