package com.trd.loginapp.states

enum class LoginState {
    LoginSuccess,
    InvalidCredentials,
    NetworkFailure,
    PhoneNumberToShort,
    PhoneCodeMissedPlusSymbol,
    EmptyPhoneCode,
    EmptyPhoneNumber,
    EmptyPassword,
    InvalidPhoneCode,
    InvalidPhoneNumber,
    UnknownFailure,
    LoginError,
}