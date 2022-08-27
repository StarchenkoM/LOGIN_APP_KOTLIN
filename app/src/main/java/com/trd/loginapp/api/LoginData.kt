package com.trd.loginapp.api

import com.google.gson.annotations.SerializedName

/*//https://tips-api-staging.crio-server.com/login

data class LoginData(
    @SerializedName("phone_code")
    val phone_code: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("password")
    val password: String,
)*/

//https://jsonplaceholder.typicode.com/guide/
data class LoginData(
    @SerializedName("title")
    val phone_code: String,
    @SerializedName("body")
    val phone_number: String,
    @SerializedName("userId")
    val password: Int,
)
