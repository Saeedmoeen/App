package com.example.app.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.Adapter
import com.example.app.api.Api
import com.example.app.api.RetrofitClient
import com.example.app.models.Item
import com.example.app.utlits.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var sessionManager: SessionManager

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    this@HomeActivity.window.decorView.windowInsetsController
                        ?.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                }
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        manager = LinearLayoutManager(this)
        sessionManager = SessionManager(this)
        getAllItem()
    }

    fun getAllItem() {
        val retrofit = RetrofitClient.getRetrofitInstance().create(Api::class.java)

        retrofit.getAllItem(token = "Token ${sessionManager.fetchAuthToken()}").enqueue(object : Callback<List<Item>>{
            override fun onResponse(
                call: Call<List<Item>>,
                response: Response<List<Item>>
            ) {
                if(response.isSuccessful){
                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                    val manager = LinearLayoutManager(this@HomeActivity)
                    recyclerView.layoutManager = manager
                    recyclerView.setHasFixedSize(true)
                    adapter = Adapter(response.body()!!)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}