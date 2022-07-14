package com.example.app.ui.home

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import com.example.app.R
import com.example.app.databinding.ActivityDescriptionBinding

@Suppress("DEPRECATION")
class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    this@DescriptionActivity.window.decorView.windowInsetsController
                        ?.setSystemBarsAppearance(
                            0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                }
            }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getItemIntent()

    }

    private fun getItemIntent(){
        val title = intent.getStringExtra("title")
        val code = intent.getStringExtra("code")
        val category = intent.getStringExtra("category")
        val info = intent.getStringExtra("info")
        val price = intent.getStringExtra("price")
        val quantity = intent.getStringExtra("quantity")

        setData(title,code,category,info,price,quantity)
    }

    private fun setData(title: String?, code: String?, category: String?, info: String?, price: String?, quantity: String?) {
        binding.tvTitle.setText(title)
        binding.tvCode.setText(code)
        binding.tvCategory.setText(category)
        binding.tvDescription.setText(info)
        binding.tvPrice.setText(price)
        binding.tvQuantity.setText(quantity)
    }
}