package com.trd.loginapp.repository

import android.util.Log
import com.trd.loginapp.api.LoginApi
import com.trd.loginapp.api.LoginData
import com.trd.loginapp.database.DataMapper
import com.trd.loginapp.database.UserDao
import com.trd.loginapp.states.LoadingState
import com.trd.loginapp.states.LoginState
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val userDao: UserDao,
    private val dataMapper: DataMapper,
) : AppRepository {
    override suspend fun postLoginData(loginData: LoginData): LoginState {
        val response = loginApi.postLoginData(loginData)
        Log.i("***888", "postLoginData: responce = $response")
        Log.i("***888", "postLoginData: responce.body() = ${response.body()}")
        return if (response.isSuccessful) {
            val body = response.body()
            val dbItem = body?.let { dataMapper.mapApiItemToDBEntity(it) }
            dbItem?.let { userDao.insertUserToDb(it) }
            LoginState.LoginSuccess
        } else {
            LoginState.LoginError
        }
    }

    override suspend fun getUserById(phoneNumber: String): LoadingState {
        Log.i("***888", "getUserById: phoneNumberDBrequest = $phoneNumber")
        val dbItem = userDao.getUserById(phoneNumber)
        return if (dbItem != null) {
            val userItem = dataMapper.mapDBItemToUserItem(dbItem)
            LoadingState.LoadingSuccess(userItem)
        } else {
            LoadingState.LoadingError
        }
    }

}