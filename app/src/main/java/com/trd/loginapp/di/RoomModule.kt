package com.trd.loginapp.di

import android.content.Context
import androidx.room.Room
import com.trd.loginapp.database.DatabaseRoom
import com.trd.loginapp.database.UserDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext applicationContext: Context
    ): DatabaseRoom = Room.databaseBuilder(
        applicationContext,
        DatabaseRoom::class.java, "user-database"
    ).build()

    @Singleton
    @Provides
    fun provideNewsDao(appDatabase: DatabaseRoom): UserDao = appDatabase.userDao()


}

