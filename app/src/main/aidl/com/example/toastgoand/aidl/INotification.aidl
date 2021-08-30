// INotification.aidl

package com.example.toastgoand.aidl;
// Declare any non-default types here with import statements

interface INotification {
    void onError(int error);
    void onTokenWillExpire();
}
