package com.hababk.delivery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hababk.delivery.R;
import com.hababk.delivery.utils.pojoclasses.Address;

import java.util.ArrayList;

/**
 * Created by a_man on 20-02-2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Address> dataList;
    private int lastSelectedPos;

    public AddressAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.dataList.add(new Address("Home", "104, Adina park, Opp SBI bank, sardar nagar, Mulund (W), Mumbai - 400080"));
        this.dataList.add(new Address("Office", "240, Second floor, worl trade center, new McDonals, Andheri west, Mumbai 400058"));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address, parent, false));
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
        private TextView title, addressText;
        private RadioButton selectedRadio;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.addressTitle);
            addressText = itemView.findViewById(R.id.addressText);
            selectedRadio = itemView.findViewById(R.id.addressSelected);

            selectedRadio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedRadio.isChecked()) {
                        lastSelectedPos = getAdapterPosition();
                        notifyDataSetChanged();
                    }
                }
            });
        }

        public void setData(Address address) {
            title.setText(address.getTitle());
            addressText.setText(address.getAddress());

            address.setSelected(getAdapterPosition() == lastSelectedPos);
            selectedRadio.setChecked(address.isSelected());
        }
    }
}
