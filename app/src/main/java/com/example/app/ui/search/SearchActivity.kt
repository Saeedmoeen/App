package com.example.app.ui.search

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.SearchViewAdapter
import com.example.app.api.Api
import com.example.app.api.RetrofitClient
import com.example.app.databinding.ActivitySearchBinding
import com.example.app.models.search.ListSearch
import com.example.app.utlits.SessionManager
import com.example.example.Search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchViewAdapter: SearchViewAdapter
    private lateinit var retrofit: Api
    private lateinit var searchList: ArrayList<Search>

    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    this@SearchActivity.window.decorView.windowInsetsController
                        ?.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                }
            }
        }
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.etSearch.postDelayed({
            binding.etSearch.requestFocus()
            showKeyboard()
        }, 400)

        recyclerView = binding.recyclerView
        searchList = ArrayList()
        sessionManager = SessionManager(this)
        retrofit = RetrofitClient.getRetrofitInstance().create(Api::class.java)
        initRecyclerView()
        getItem()
        binding.etSearch.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                filter(binding.etSearch.text.toString())
            }

        })

    }


    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun getItem() {
        retrofit.searchItem(token = "Token ${sessionManager.fetchAuthToken()}",)
            .enqueue(object : Callback<List<Search>> {
                override fun onResponse(call: Call<List<Search>>, response: Response<List<Search>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.e("TAG", "onResponse: " + response.body())
                            searchList.addAll(response.body()!!)
                            searchViewAdapter.notifyDataSetChanged()
                        }
                    } else {
                        Log.e("TAG", "onResponse: " + response.message())
                    }
                }

                override fun onFailure(call: Call<List<Search>>, t: Throwable) {
                    Log.e("TAG", "onResponse: " + t.message)
                }

            })
    }

    private fun initRecyclerView() {

        searchViewAdapter = SearchViewAdapter(searchList, this@SearchActivity)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchViewAdapter
        }
    }

    private fun filter(text: String) {
        val filterName = ArrayList<Search>()
        searchList.filterTo(filterName) {
            it.name?.toLowerCase()?.contains(text.toLowerCase()) == true || it.code.toString().contains(text)
        }
        if (filterName != null) {
            searchViewAdapter.filterList(filterName)
        }
    }
}


