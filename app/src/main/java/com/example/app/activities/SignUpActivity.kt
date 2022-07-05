package com.example.app.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.app.R
import com.example.app.api.RetrofitClient
import com.example.app.databinding.ActivitySignUpBinding
import com.example.app.models.DefaultResponse
import com.example.app.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

                RetrofitClient.instance.createUser(fName, lName, email, username, password)
                    .enqueue(object : Callback<UserResponse> {
                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }

                        override fun onResponse(
                            call: Call<UserResponse>,
                            response: Response<UserResponse>
                        ) {
                            Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                        }

                    })
            }
    }
}