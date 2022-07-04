package com.example.app.api

import com.example.app.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("users/signup")
    @FormUrlEncoded
    fun createUser(
        @Query("first_name") fName: String,
        @Query("last_name") lName: String,
        @Query("email") email: String,
        @Query("username") username: String,
        @Query("password") password: String,
    ) : Call<DefaultResponse>
}