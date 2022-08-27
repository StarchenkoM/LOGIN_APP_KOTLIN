package com.trd.loginapp.states

enum class LoginState {
    LoginSuccess,
    NetworkFailure,
    PhoneNumberTooShort,
    MissedPlusSymbolError,
    EmptyPhoneNumber,
    EmptyPassword,
    InvalidPhoneNumber,
    UnknownFailure,
    LoginError,
    Loading,
}