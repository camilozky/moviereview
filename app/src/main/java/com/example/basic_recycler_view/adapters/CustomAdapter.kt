package com.example.basic_recycler_view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.services.ApiMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.imageItem
import kotlinx.android.synthetic.main.list_item.view.original_title
import kotlinx.android.synthetic.main.list_item.view.ratingBar

class CustomAdapter(private val listener: AdapterEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: ArrayList<ApiMovie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        com.example.basic_recycler_view.R.layout.list_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position], listener)
    }

    fun addAll(items: ArrayList<ApiMovie>) {
        itemList.addAll(items)
        notifyItemRangeInserted(itemList.size - items.size, items.size)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var item: ApiMovie

        @SuppressLint("SetTextI18n")
        fun bindItem(item: ApiMovie, listener: AdapterEvents?) {
            this.item = item
            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500" + item.poster_path)
                    .centerCrop()
                    .resize(1000, 1000)
                    .into(itemView.imageItem)
            itemView.original_title.text = item.original_title
            itemView.ratingBar.rating = (item.vote_average?.toFloat() ?: 0f) / 2
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}
