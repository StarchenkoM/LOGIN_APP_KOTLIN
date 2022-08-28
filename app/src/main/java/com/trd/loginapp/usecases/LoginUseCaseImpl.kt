package com.trd.loginapp.usecases

import android.util.Patterns
import com.trd.loginapp.Constants.PASSWORD_MIN_LENGTH
import com.trd.loginapp.Constants.PHONE_NUMBER_MIN_LENGTH
import com.trd.loginapp.api.LoginData
import com.trd.loginapp.repository.AppRepository
import com.trd.loginapp.states.LoginState
import com.trd.loginapp.states.LoginState.*
import com.trd.loginapp.utils.NetworkHelper
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AppRepository,
    private val networkHelper: NetworkHelper,
) : LoginUseCase {

    override suspend fun login(phoneNumber: String, password: String): LoginState {
        return if (!networkHelper.isOnline()) {
            NetworkFailure
        } else if (phoneNumber.isEmpty()) {
            EmptyPhoneNumber
        } else if (!phoneNumber.contains('+')) {
            MissedPlusSymbolError
        } else if (phoneNumber.length < PHONE_NUMBER_MIN_LENGTH) {
            PhoneNumberTooShort
        } else if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            InvalidPhoneNumber
        } else if (password.isEmpty()) {
            EmptyPassword
        } else if (password.length < PASSWORD_MIN_LENGTH) {
            PasswordTooShortError
        } else {

            /*TODO
               As another server is used for making POST request
               there is some difference in data structure.
               This server accept POST next parameters
                    String -> title: 'foo',
                    String -> body: 'bar',
                    Int    -> userId: 1,
                    That's why as password parameter its length is passed

               https://jsonplaceholder.typicode.com/guide/
             */
            val code = phoneNumber.substring(0, 4)
            val number = phoneNumber.substring(4, phoneNumber.lastIndex + 1)
            val pass = password.length
            val loginData = LoginData(code, number, pass)
            repository.postLoginData(loginData)
        }

    }


}