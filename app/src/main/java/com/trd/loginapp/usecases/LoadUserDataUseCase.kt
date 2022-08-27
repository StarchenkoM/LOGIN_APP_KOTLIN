package com.trd.loginapp.usecases

import com.trd.loginapp.states.LoadingState

interface LoadUserDataUseCase {
    suspend fun loadUserData(userPhoneNumber: String): LoadingState
}