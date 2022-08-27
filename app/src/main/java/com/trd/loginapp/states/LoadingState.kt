package com.trd.loginapp.states

import com.trd.loginapp.database.UserItem

sealed class LoadingState {
    data class LoadingSuccess(val userItem: UserItem) : LoadingState()
    object DataLoading : LoadingState()
    object LoadingError : LoadingState()
}