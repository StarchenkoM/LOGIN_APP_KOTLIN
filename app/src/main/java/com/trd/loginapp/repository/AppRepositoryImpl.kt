package com.trd.loginapp.repository

import android.util.Log
import com.trd.loginapp.api.LoginApi
import com.trd.loginapp.api.LoginData
import com.trd.loginapp.api.LoginDataApiResponse
import com.trd.loginapp.database.DataMapper
import com.trd.loginapp.database.UserDao
import com.trd.loginapp.states.LoadingState
import com.trd.loginapp.states.LoadingState.LoadingError
import com.trd.loginapp.states.LoadingState.LoadingSuccess
import com.trd.loginapp.states.LoginState
import com.trd.loginapp.states.LoginState.LoginError
import com.trd.loginapp.states.LoginState.LoginSuccess
import retrofit2.Response
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
        return provideLoginResult(response)
    }

    private fun provideLoginResult(response: Response<LoginDataApiResponse>): LoginState {
        return if (response.isSuccessful) {
            val savingResult = saveResponseToDB(response)
            if (savingResult > 0) LoginSuccess else LoginError
        } else {
            LoginError
        }
    }

    private fun saveResponseToDB(response: Response<LoginDataApiResponse>): Long {
        val dbItem = response.body()?.let { dataMapper.mapApiItemToDBEntity(it) }
        val savingResult = dbItem?.let { userDao.insertUserToDb(it) } ?: -1
        return savingResult
    }

    override suspend fun getUserById(phoneNumber: String): LoadingState {
        Log.i("***888", "getUserById: phoneNumberDBrequest = $phoneNumber")
        val dbItem = userDao.getUserById(phoneNumber)
        return if (dbItem != null) {
            val userItem = dataMapper.mapDBItemToUserItem(dbItem)
            LoadingSuccess(userItem)
        } else {
            LoadingError
        }
    }

}