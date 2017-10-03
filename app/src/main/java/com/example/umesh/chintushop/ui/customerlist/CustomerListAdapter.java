package com.example.umesh.chintushop.ui.customerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umesh.chintushop.R;
import com.example.umesh.chintushop.core.listeners.OnCustomerSelectedListener;
import com.example.umesh.chintushop.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Umesh on 27-09-2017.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    private List<Customer> mCustomers;
    private final Context mContext;
    private final OnCustomerSelectedListener mListener;
    private boolean shouldHighlightSelectedCustomer = false;
    private int selectedPosition = 0;

    public CustomerListAdapter(List<Customer> customers, Context context, OnCustomerSelectedListener listener) {
        mCustomers = customers;
        mContext = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Customer selectedCustomer = mCustomers.get(position);

        holder.customerName.setText(selectedCustomer.getCustomerName());
        holder.customerEmail.setText(selectedCustomer.getEmailAddess());
        Picasso.with(mContext)
                .load(selectedCustomer.getProfileImagePath())
                .fit()
                .placeholder(R.drawable.profile_icon)
                .into(holder.customerHeadShot);

        if (shouldHighlightSelectedCustomer){
            if (selectedPosition == position){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.primary_light));
            }else {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            }
        }else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    @Override
    public int getItemCount() {
        if (mCustomers != null) {
            return mCustomers.size();
        } else {
            return  0;
        }
    }

    public void replaceData(List<Customer> customers){
        mCustomers = customers;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.image_view_customer_head_shot) ImageView customerHeadShot;
        @Bind(R.id.text_view_customers_nam) TextView customerName;
        @Bind(R.id.text_view_customers_email) TextView customerEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {

            shouldHighlightSelectedCustomer = true;
            selectedPosition = getLayoutPosition();
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onSelectCustomer(selectedCustomer);
            notifyDataSetChanged();

        }

        @Override
        public boolean onLongClick(View view) {
            Customer selectedCustomer = mCustomers.get(selectedPosition);
            mListener.onLongClickCustomer(selectedCustomer);
            return true;
        }
    }
}
