package com.trd.loginapp.utils

import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.StringRes

interface ToastUtils {
    fun showToast(message: String, duration: Int = LENGTH_SHORT)
    fun showToast(@StringRes messageResId: Int, duration: Int = LENGTH_SHORT)
    fun showNetworkErrorToast()
    fun showUnknownErrorToast()
    fun showEmptyPhoneErrorToast()
    fun showEmptyPasswordErrorToast()
    fun showPasswordTooShortErrorToast()
    fun showInvalidPhoneNumberErrorToast()
    fun showLoginErrorToast()
    fun showPhoneNumberTooShortErrorToast()
    fun showMissedPlusSymbolErrorToast()
}