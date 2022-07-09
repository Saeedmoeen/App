package com.example.app.ui.auth

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
import com.example.app.api.RetrofitInstance
import com.example.app.databinding.ActivitySignInBinding
import com.example.app.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    this@SignInActivity.window.decorView.windowInsetsController
                        ?.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                }
            }
        }
        super.onCreate(savedInstanceState)
        val binding: ActivitySignInBinding =
            DataBindingUtil.setContentView(this@SignInActivity, R.layout.activity_sign_in)

        binding.btnSignIn.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPw.text.toString().trim()
            signIn(username, password)
        }
    }

    private fun signIn(username: String, password: String) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(Api::class.java)

        retIn.login(username, password).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    this@SignInActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 200) Toast.makeText(
                    this@SignInActivity,
                    "Login success!",
                    Toast.LENGTH_SHORT
                ).show()
                else Toast.makeText(this@SignInActivity, "Login failed!", Toast.LENGTH_SHORT).show()

            }
        })
    }
}
