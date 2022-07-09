package com.example.app.api

import com.example.app.models.Item
import com.example.app.models.LoginResponse
import com.example.app.models.User
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("users/signup")
    @FormUrlEncoded
    fun createUser(
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
    ) : Call<User>

    @POST("users/signin")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : Call<LoginResponse>

    @GET("products")
    fun getAllItem(@Header("Authorization") token: String): Call<List<Item>>
}