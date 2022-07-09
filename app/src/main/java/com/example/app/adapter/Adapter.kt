package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

import com.example.app.models.Item

class Adapter(private val item: List<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) {
            val title = view.findViewById<TextView>(R.id.tvTitle)
            val code = view.findViewById<TextView>(R.id.tvNumber)
            val quantity = view.findViewById<TextView>(R.id.tvQuantity)
            val price = view.findViewById<TextView>(R.id.tvPrice)
            val available = view.findViewById<TextView>(R.id.tvAvailable)
            val category = view.findViewById<TextView>(R.id.tvCategory)

            title.text = item.results[0].name
            code.text = item.results[0].code.toString()
            quantity.text = item.results[0].quantities[0].quantity.toString()
            price.text = item.results[0].prices[0].price.toString()
            available.text = item.results[0].active.toString()
            category.text = item.results[0].category.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }
}