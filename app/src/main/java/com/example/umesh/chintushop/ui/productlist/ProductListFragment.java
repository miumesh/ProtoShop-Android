package com.example.umesh.chintushop.ui.productlist;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.umesh.chintushop.R;
import com.example.umesh.chintushop.core.listeners.OnProductSelectedListener;
import com.example.umesh.chintushop.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements OnProductSelectedListener{

    private View mRootView;
    private ProductListAdapter mAdapter;

    @Bind(R.id.product_list_recyclerview) RecyclerView mRecyclerView;
    @Bind(R.id.empty_text) TextView mEmptyText;
    @Bind(R.id.fab) FloatingActionButton mFab;


    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, mRootView);

        //setup Adapter
        List<Product> tempProducts = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ProductListAdapter(tempProducts,getActivity(),this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if (tempProducts.size() < 1){
            showEmptyTextMessage();
        } else{
            hideEmptyTextMessage();
        }

        return mRootView;


    }

    private void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyText.setVisibility(View.GONE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSelectedProduct(Product selectedProduct) {

    }

    @Override
    public void onLongClickProduct(Product clickedProduct) {

    }
}
