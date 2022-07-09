package com.example.app.ui.auth

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.app.R
import com.example.app.api.Api
import com.example.app.api.RetrofitClient
import com.example.app.databinding.ActivitySignUpBinding
import com.example.app.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal const val BASE_URL = "http://194.62.43.26:1337/api/"

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

                signUp(fName, lName, email, username, password)
            }
    }

    private fun signUp(
        fName: String,
        lName: String,
        email: String,
        username: String,
        password: String
    ) {
        val retrofit = RetrofitClient.getRetrofitInstance().create(Api::class.java)

        retrofit.createUser(fName, lName, email, username, password).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 201) {
                    alertDialog("اکانت شما با موفیت ساخته شد")
                } else if (response.code() == 403) {
                    alertDialog("ارتباط شما با سرور برقرار نمیباشد")
                } else if (response.code() == 400) {
                    alertDialog("این اکانت قبلا ساخته شده")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(
                    this@SignUpActivity,
                    t.localizedMessage!!.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun alertDialog(description: String) {
        val builder = AlertDialog.Builder(this@SignUpActivity, R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.alert_dialog, null)
        val button = view.findViewById<Button>(R.id.dialogDismiss_button)
        builder.setView(view)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvDescription.text = description
        tvTitle.text = "پیام"
        button.setOnClickListener {
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }
}