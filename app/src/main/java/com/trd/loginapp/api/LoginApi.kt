package com.trd.loginapp.api


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    /*
    Method: POST
    ● URL: https://tips-api-staging.crio-server.com/login
    ● Parameters: phone_code - String (pass in query) - country phone
    code; phone_number - String (pass in query) - phone number without
    country code; password - String (pass in query) - user password */
/*
   @POST("login")
    suspend fun postLoginData(
        @Body loginData: LoginData
    ): Response<LoginDataApiResponse>*/

    //https://jsonplaceholder.typicode.com/guide/
    @POST("posts")
    suspend fun postLoginData(
        @Body loginData: LoginData
    ): Response<LoginDataApiResponse>


}