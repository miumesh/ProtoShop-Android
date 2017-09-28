package com.example.umesh.chintushop.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.umesh.chintushop.ui.checkout.CheckoutFragment;
import com.example.umesh.chintushop.ui.customerlist.CustomerListFragment;
import com.example.umesh.chintushop.ui.productlist.ProductListFragment;

/**
 * Created by Umesh on 28-09-2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment;
        switch (position) {
            case 0:
                selectedFragment = new ProductListFragment();
                break;
            case 1:
                selectedFragment = new CustomerListFragment();
                break;
            case 2:
                selectedFragment = new CheckoutFragment();
                break;
            default:
                selectedFragment = new ProductListFragment();
        }
        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";
        switch (position){
            case 0:
                title = "Products";
                break;
            case 1:
                title = "Customers";
                break;
            case 2:
                title = "Cart";
                break;
        }
        return title;
    }
}
