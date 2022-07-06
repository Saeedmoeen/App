package com.example.app.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.app.R
import com.example.app.api.Api
import com.example.app.databinding.ActivitySignUpBinding
import com.example.app.models.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://194.62.43.26:1337/api/"
private const val TAG = "SignupActivity"

class SignUpActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    this@SignUpActivity.window.decorView.windowInsetsController
                        ?.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                }
            }
        }
        super.onCreate(savedInstanceState)
        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this@SignUpActivity, R.layout.activity_sign_up)

        binding.btnSignUp
            .setOnClickListener {

                val username = binding.etUsername.text.toString().trim()
                val password = binding.etPw.text.toString().trim()
                val fName = binding.etFName.text.toString().trim()
                val lName = binding.etLName.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()

                if (username.isEmpty()) {
                    binding.etUsername.error = "Password required"
                    binding.etUsername.requestFocus()
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    binding.etPw.error = "Password required"
                    binding.etPw.requestFocus()
                    return@setOnClickListener
                }

                fun getClient(): Retrofit? {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    val client: OkHttpClient = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .retryOnConnectionFailure(true)
                        .build()
                    return Retrofit.Builder()
                        .baseUrl("http://194.62.43.26:1337/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
                }

                val call = getClient()!!.create(Api::class.java)
                    .createUser(fName, lName, email, username, password)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        Toast.makeText(
                            this@SignUpActivity,
                            response.code().toString() + " " + response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, t.localizedMessage!!.toString(), Toast.LENGTH_LONG).show()
                    }
                })

            }
    }
}