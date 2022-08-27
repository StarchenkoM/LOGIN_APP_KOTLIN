package com.trd.loginapp.usecases

import com.trd.loginapp.states.LoginState

interface LoginUseCase {
    suspend fun login(phoneNumber: String, password: String): LoginState
}