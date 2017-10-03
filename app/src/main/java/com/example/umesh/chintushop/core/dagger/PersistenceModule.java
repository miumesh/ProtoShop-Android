package com.example.umesh.chintushop.core.dagger;

import android.content.Context;

import com.example.umesh.chintushop.ui.customerlist.CustomerListContract;
import com.example.umesh.chintushop.ui.customerlist.CustomerListInMemoryRepository;
import com.example.umesh.chintushop.ui.productlist.ProductInMemoryRepository;
import com.example.umesh.chintushop.ui.productlist.ProductListContract;
import com.example.umesh.chintushop.ui.transaction.TempRepository;
import com.example.umesh.chintushop.ui.transaction.TransactionContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Umesh on 03-10-2017.
 */
@Module
public class PersistenceModule {
    @Provides @Singleton
    public ProductListContract.Repository providesProductRepository(Context context){
        return new ProductInMemoryRepository();
    }

    @Provides @Singleton
    public CustomerListContract.Repository providesCustomerRepository(Context context){
        return new CustomerListInMemoryRepository();
    }

    @Provides
    public TransactionContract.Repository providesTransactionRespository(Context context){
        return new TempRepository();
    }


}


