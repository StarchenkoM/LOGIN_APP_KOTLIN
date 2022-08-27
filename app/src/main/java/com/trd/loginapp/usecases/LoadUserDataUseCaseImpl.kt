package com.trd.loginapp.usecases

import com.trd.loginapp.repository.AppRepository
import com.trd.loginapp.states.LoadingState

class LoadUserDataUseCaseImpl(private val appRepository: AppRepository) : LoadUserDataUseCase {
    override suspend fun loadUserData(userPhoneNumber: String): LoadingState {
        return appRepository.getUserById(userPhoneNumber)
    }
}