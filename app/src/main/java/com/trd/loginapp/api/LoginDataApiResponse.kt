package com.trd.loginapp.api

import com.google.gson.annotations.SerializedName

/*
//https://tips-api-staging.crio-server.com/login

data class LoginDataApiResponse(
    @SerializedName("surname")
    val surname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phone_number: String,
)*/

//https://jsonplaceholder.typicode.com/guide/
data class LoginDataApiResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val phone_code: String,
    @SerializedName("body")
    val phone_number: String,
    @SerializedName("userId")
    val userId: Int,
)
