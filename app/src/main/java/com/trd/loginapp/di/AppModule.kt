package com.trd.loginapp.di

import com.trd.loginapp.database.DataMapper
import com.trd.loginapp.database.UserDao
import com.trd.loginapp.repository.AppRepository
import com.trd.loginapp.repository.AppRepositoryImpl
import com.trd.loginapp.usecases.LoadUserDataUseCase
import com.trd.loginapp.usecases.LoadUserDataUseCaseImpl
import com.trd.loginapp.usecases.LoginUseCase
import com.trd.loginapp.usecases.LoginUseCaseImpl
import com.trd.loginapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @IoCoroutineScope
    @Provides
    fun providesIoDispatcher(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)


    //Repositories
    @Provides
    fun provideLoginRepository(
        userDao: UserDao,
        dataMapper: DataMapper,
    ): AppRepository = AppRepositoryImpl(userDao, dataMapper)

    //Use Cases
    @Provides
    fun provideLoginUseCase(
        appRepository: AppRepository,
        networkHelper: NetworkHelper,
    ): LoginUseCase =
        LoginUseCaseImpl(appRepository, networkHelper)

    @Provides
    fun provideLoadUserDataUseCase(
        appRepository: AppRepository,
    ): LoadUserDataUseCase =
        LoadUserDataUseCaseImpl(appRepository)

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoCoroutineScope
