package com.example.basic_recycler_view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.services.ApiMovie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter(private val listener: AdapterEvents) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: ArrayList<ApiMovie> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
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

        fun bindItem(item: ApiMovie, listener: AdapterEvents?) {
            Picasso.get()
                .load("http://image.tmdb.org/t/p/w500" + item.poster_path)
                .centerCrop()
                .resize(1000, 1000)
                .into(itemView.imageItem)
            itemView.popularity.text = item.popularity
            itemView.vote_count.text = item.vote_count
            itemView.video.text = item.video
            itemView.adult.text = item.adult
            itemView.original_language.text = item.original_language
            itemView.original_title.text = item.original_title
            itemView.genre_ids.text = item.genre_ids?.get(0)?.toString()
            itemView.title.text = item.title
            itemView.vote_average.text = item.vote_average
            itemView.overview.text = item.overview
            itemView.release_date.text = item.release_date
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}
