package com.example.umesh.chintushop.core.dagger;

import com.example.umesh.chintushop.common.MainActivity;
import com.example.umesh.chintushop.common.ShoppingCart;
import com.example.umesh.chintushop.ui.customerlist.CustomerPresenter;
import com.example.umesh.chintushop.ui.productlist.ProductPresenter;
import com.example.umesh.chintushop.ui.transaction.TransactionPresenter;
import com.example.umesh.chintushop.ui.transaction.TransactionSQLiteManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Umesh on 21-09-2017.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ShoppingCartModule.class,
                BusModule.class,
                PersistenceModule.class
        }
)
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(ShoppingCart cart);
    void inject(ProductPresenter presenter);
    void inject(CustomerPresenter presenter);
    void inject(TransactionPresenter presenter);
    void inject(TransactionSQLiteManager manager);

}
