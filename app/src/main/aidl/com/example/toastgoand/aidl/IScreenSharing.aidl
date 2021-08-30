// IScreenSharing.aidl
package com.example.toastgoand.aidl;

import com.example.toastgoand.aidl.INotification;

// Declare any non-default types here with import statements

interface IScreenSharing {
    void registerCallback(INotification callback);
    void unregisterCallback(INotification callback);
    void startShare();
    void stopShare();
    void renewToken(String token);
}
