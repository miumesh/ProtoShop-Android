package com.example.umesh.chintushop.core.listeners;

/**
 * Created by Umesh on 03-10-2017.
 */

public interface OnDatabaseOperationCompleteListener {
    void onDatabaseOperationFailed(String error);
    void onDatabaseOperationSucceded(String message);
}
