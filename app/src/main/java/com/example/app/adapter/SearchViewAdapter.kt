package com.example.app.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.example.Search


class SearchViewAdapter(private var searchList: List<Search>, private val context: Context) :
    RecyclerView.Adapter<SearchViewAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.tvTitle)
        private val code = view.findViewById<TextView>(R.id.tvCode)
        private val info = view.findViewById<TextView>(R.id.tvInfo)

        fun bind(item: Search) {
            title.text = item.name
            code.text = item.code.toString()
            info.text = item.info
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_search, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(searchList.get(position))
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    fun filterList(filterName: ArrayList<Search>) {
        Log.e("TAG", "filterList: $filterName")
        Log.e("TAG", "filterList: " + filterName.size.toString())
        this.searchList = filterName
        notifyDataSetChanged()
    }
}