package com.trd.loginapp.di

import android.content.Context
import com.trd.loginapp.utils.NetworkHelper
import com.trd.loginapp.utils.NetworkHelperImpl
import com.trd.loginapp.utils.ToastUtils
import com.trd.loginapp.utils.ToastUtilsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Singleton
    @Provides
    fun provideToastUtils(@ApplicationContext context: Context): ToastUtils =
        ToastUtilsImpl(context)

    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper =
        NetworkHelperImpl(context)

}
