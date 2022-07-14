@file:Suppress("DEPRECATION")

package com.example.app.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.ItemViewAdapter
import com.example.app.api.Api
import com.example.app.api.RetrofitClient
import com.example.app.databinding.ActivityHomeBinding
import com.example.app.models.products.ListItem
import com.example.app.models.products.Results
import com.example.app.ui.cart.CartActivity
import com.example.app.ui.category.CategoryActivity
import com.example.app.ui.search.SearchActivity
import com.example.app.utlits.SessionManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {

    private var page: Int = 1
    private lateinit var sessionManager: SessionManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemViewAdapter: ItemViewAdapter
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var retrofit: Api
    private lateinit var binding: ActivityHomeBinding

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
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerView = binding.recyclerView
        sessionManager = SessionManager(this)
        bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.selectedItemId = R.id.homeNav

        initBottomNavigationView()

        retrofit = RetrofitClient.getRetrofitInstance().create(Api::class.java)
        initRecyclerView()
        getAllItem()

        binding.tvSearch.setOnClickListener {
            val intent = Intent(this@HomeActivity, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getAllItem() {

        retrofit.getAllItem(token = "Token ${sessionManager.fetchAuthToken()}", page)
            .enqueue(object : Callback<ListItem> {
                override fun onResponse(
                    call: Call<ListItem>,
                    response: Response<ListItem>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            itemViewAdapter.setListItem(it.results)
                            page += 1
                        }
                    } else {
                        Log.e("TAG", "onResponse: " + response.message())
                    }
                }

                override fun onFailure(call: Call<ListItem>, t: Throwable) {
                    Log.e("TAG", "onFailure: ", t)
                }
            })
    }

    private fun initRecyclerView() {

        itemViewAdapter = ItemViewAdapter(this@HomeActivity)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = itemViewAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (newState == 1) {
                        getAllItem()
                    }
                }
            }
            )
        }
    }

    private fun initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeNav -> {
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.shoppingCartNav -> {
                    val intent = Intent(this@HomeActivity, CartActivity::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.categoryNav -> {
                    val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}