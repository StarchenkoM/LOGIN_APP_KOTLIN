package com.trd.loginapp.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trd.loginapp.states.LoginState
import com.trd.loginapp.states.LoginState.Loading
import com.trd.loginapp.states.LoginState.LoginError
import com.trd.loginapp.usecases.LoginUseCase
import com.trd.loginapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val _loginStateLiveData = MutableLiveData<LoginState>()
    val loginStateLiveData: LiveData<LoginState> get() = _loginStateLiveData

    fun login(phoneNumber: String, password: String) {
        _loginStateLiveData.postValue(Loading)
        if (networkHelper.isOnline()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val state = loginUseCase.login(phoneNumber, password)
                    _loginStateLiveData.postValue(state)
//                    _loginStateLiveData.postValue(LoginError)
                } catch (e: Exception) {
                    _loginStateLiveData.postValue(LoginError)
                }
            }
        }
    }
}