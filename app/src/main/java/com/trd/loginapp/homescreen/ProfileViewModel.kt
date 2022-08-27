package com.trd.loginapp.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trd.loginapp.states.LoadingState
import com.trd.loginapp.usecases.LoadUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loadUserDataUseCase: LoadUserDataUseCase,
) : ViewModel() {

    private val _userLoadingStateLiveData = MutableLiveData<LoadingState>()
    val userLoadingStateLiveData: LiveData<LoadingState> get() = _userLoadingStateLiveData

    fun loadUserData(userPhoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val state = loadUserDataUseCase.loadUserData(userPhoneNumber)
            _userLoadingStateLiveData.postValue(state)
        }
    }
}