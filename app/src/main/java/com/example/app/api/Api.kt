package com.example.app.api

import com.example.app.models.DefaultResponse
import com.example.app.models.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @POST("users/signup")
    @FormUrlEncoded
    fun createUser(
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
    ) : Call<UserResponse>
}