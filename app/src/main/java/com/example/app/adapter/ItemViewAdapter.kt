package com.example.app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.models.products.Results
import com.example.app.ui.home.DescriptionActivity
import java.security.AccessController.getContext

class ItemViewAdapter(private val context: Context) :
    RecyclerView.Adapter<ItemViewAdapter.MyViewHolder>() {

    private var itemList: MutableList<Results> = mutableListOf()
    private var page: Int = 0

    fun setListItem(item: List<Results>) {
        itemList.addAll(item)
        notifyItemRangeInserted(page * 10, (page.plus(1) * 10) - 1)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.tvTitle)
        private val code = view.findViewById<TextView>(R.id.tvNumber)
        private val quantity = view.findViewById<TextView>(R.id.tvQuantity)
        private val price = view.findViewById<TextView>(R.id.tvPrice)
        private val available = view.findViewById<TextView>(R.id.tvAvailable)
        private val category = view.findViewById<TextView>(R.id.tvCategory)

        fun bind(item: Results, context: Context) {
            title.text = item.name
            code.text = item.code.toString()
            quantity.text = item.quantities[0].quantity.toString()
            price.text = item.prices[0].price.toString()
            available.text = item.active.toString()
            category.text = item.category?.name
            itemView.setOnClickListener {
                val intent = Intent(context, DescriptionActivity::class.java)
                intent.putExtra("title", title.text)
                intent.putExtra("code", code.text)
                intent.putExtra("quantity", quantity.text)
                intent.putExtra("price", price.text)
                intent.putExtra("category", category.text)
                intent.putExtra("info", item.info.toString())
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = itemList[position]
        holder.bind(data, context)
    }
}