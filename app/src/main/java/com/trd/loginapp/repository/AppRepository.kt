package com.trd.loginapp.repository

import com.trd.loginapp.api.LoginData
import com.trd.loginapp.states.LoadingState
import com.trd.loginapp.states.LoginState

interface AppRepository {
    suspend fun postLoginData(loginData: LoginData): LoginState
    suspend fun getUserById(phoneNumber: String): LoadingState
}