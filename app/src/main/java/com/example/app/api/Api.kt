package com.example.app.api

import com.example.app.models.LoginResponse
import com.example.app.models.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

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
}

class RetrofitInstance {
    companion object {
        val BASE_URL: String = "http://194.62.43.26:1337/api/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}