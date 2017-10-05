package com.example.umesh.chintushop.common;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.umesh.chintushop.R;
import com.example.umesh.chintushop.core.ChintuShopApplication;
import com.example.umesh.chintushop.core.events.CustomerSelectedEvent;
import com.example.umesh.chintushop.core.events.UpdateToolbarEvent;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.util.Formatter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) ViewPager mViewPager;
    @Bind(R.id.tabs) TabLayout mTabLayout;

    @Bind(R.id.text_view_number_of_items) TextView mQtyTextView;
    @Bind(R.id.text_view_total_amount) TextView mTotalTextView;
    @Bind(R.id.text_view_customer_name) TextView mNameTextView;

    private Bus mBus;
    @Inject ShoppingCart mCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ButterKnife.bind(this);
        setupViewPager();

        mBus = ChintuShopApplication.getInstance().getBus();

        /*DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();*/

        //openFragment(new ProductListFragment(), "Product List");
        //openFragment(new CustomerListFragment(), "Customer List");
        //openFragment(new CheckoutFragment(), "Cart");




    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }




    /*private void openFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(title);
    }*/



    @Override
    protected void onPause() {
        super.onPause();
        ChintuShopApplication.getInstance().getAppComponent().inject(this);
        mCart.saveCartToPreference();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ChintuShopApplication.getInstance().getAppComponent().inject(this);
            mCart.saveCartToPreference();
            mBus.unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Subscribe
    public void onUpdateToolbar(UpdateToolbarEvent event){
        /*populateToolbar(event.getLineItems());*/
    }

    @Subscribe
    public void onCustomerSelected(CustomerSelectedEvent event){
        if (event.isClearCustomer()) {
            /*mNameTextView.setText(getString(R.string.hint_customer_name));*/
        } else {
            mNameTextView.setText(event.getSelectedCustomer().getCustomerName());
        }
    }


    private void populateToolbar(List<LineItem> listOfItemsInShoppingCart) {
        double totalAmount = 0;
        int numberOfItems = 0;
        if (listOfItemsInShoppingCart != null && listOfItemsInShoppingCart.size() > 0) {
            for (LineItem item: listOfItemsInShoppingCart){
                totalAmount += item.getSumPrice();
                /*numberOfItems += item.getQauntity();*/
            }
            mTotalTextView.setText(Formatter.formatCurrency(totalAmount));

            if (numberOfItems > 1){
                mQtyTextView.setText(numberOfItems + " items" );
            }else {
                mQtyTextView.setText(numberOfItems + " item" );
            }
        } else {
            mTotalTextView.setText(Formatter.formatCurrency(0.00));
            mQtyTextView.setText(0 + " item" );

        }

    }


}
