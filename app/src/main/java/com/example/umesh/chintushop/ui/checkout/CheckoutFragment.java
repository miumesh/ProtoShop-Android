package com.example.umesh.chintushop.ui.checkout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.umesh.chintushop.R;
import com.example.umesh.chintushop.core.listeners.CartActionsListener;
import com.example.umesh.chintushop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckoutFragment extends Fragment implements CartActionsListener {

    private View mRootView;
    private CheckoutAdapter mAdapter;

    @Bind(R.id.checkout_list_recyclerview) RecyclerView mRecyclerView;
    @Bind(R.id.empty_text) TextView mEmptyTextView;
    @Bind(R.id.text_view_sub_total) TextView mSubTotalTextView;
    @Bind(R.id.text_view_total) TextView mTotalTextView;
    @Bind(R.id.text_view_tax) TextView mTotalTaxRate;
    @Bind(R.id.radio_group_payment_type) RadioGroup mRadioGroup;
    @Bind(R.id.clear_cart_button) Button mClearButton;
    @Bind(R.id.checkout_cart_button) Button mCheckoutButton;





    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        ButterKnife.bind(this, mRootView);

        //Setup Adapter
        List<LineItem> tempItems = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CheckoutAdapter(tempItems, getContext(),this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        if(tempItems.size() < 1){
            showEmptyTextMessage();
        }else {
            hideEmptyTextMessage();
        }

        return mRootView;
    }

    private void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyTextView.setVisibility(View.GONE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemDeleted(LineItem item) {

    }

    @Override
    public void onItemQtyChange(LineItem item, int qty) {

    }
}
