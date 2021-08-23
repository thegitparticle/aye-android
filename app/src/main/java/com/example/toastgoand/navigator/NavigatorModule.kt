package com.example.toastgoand.navigator

import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigatorImpl(navigatorImpl: NavigatorImpl) : Navigator
}