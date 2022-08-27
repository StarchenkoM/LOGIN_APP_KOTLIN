package com.trd.loginapp.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.StringRes
import com.trd.loginapp.R

class ToastUtilsImpl(val context: Context) : ToastUtils {

    override fun showToast(message: String, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

    override fun showToast(@StringRes messageResId: Int, duration: Int) {
        Toast.makeText(context, messageResId, duration).show()
    }


    override fun showNetworkErrorToast() {
        showToast(NO_INTERNET_CONNECTION_MESSAGE, LENGTH_LONG)
    }

    override fun showUnknownErrorToast() {
        showToast(UNKNOWN_ERROR_MESSAGE, LENGTH_LONG)
    }

    override fun showEmptyPhoneErrorToast() {
        showToast(EMPTY_PHONE_NUMBER_MESSAGE, LENGTH_SHORT)
    }

    override fun showEmptyPasswordErrorToast() {
        showToast(EMPTY_PASSWORD_MESSAGE, LENGTH_SHORT)
    }

    override fun showInvalidPhoneNumberErrorToast() {
        showToast(INVALID_PHONE_NUMBER_MESSAGE, LENGTH_SHORT)
    }

    override fun showLoginErrorToast() {
        showToast(LOGIN_ERROR_MESSAGE, LENGTH_LONG)
    }

    override fun showPhoneNumberTooShortErrorToast() {
        showToast(PHONE_NUMBER_TOO_SHORT_MESSAGE, LENGTH_SHORT)
    }

    override fun showMissedPlusSymbolErrorToast() {
        showToast(PHONE_CODE_MISSED_PLUS_SYMBOL_MESSAGE, LENGTH_SHORT)
    }


    companion object {
        const val NO_INTERNET_CONNECTION_MESSAGE = R.string.no_internet_connection
        const val UNKNOWN_ERROR_MESSAGE = R.string.unknown_error
        const val EMPTY_PHONE_NUMBER_MESSAGE = R.string.empty_phone_number_error
        const val EMPTY_PASSWORD_MESSAGE = R.string.empty_password_error
        const val INVALID_PHONE_NUMBER_MESSAGE = R.string.invalid_phone_number_error
        const val LOGIN_ERROR_MESSAGE = R.string.login_error
        const val PHONE_NUMBER_TOO_SHORT_MESSAGE = R.string.phone_number_too_short_error
        const val PHONE_CODE_MISSED_PLUS_SYMBOL_MESSAGE = R.string.phone_code_missed_plus_symbol_error
    }
}