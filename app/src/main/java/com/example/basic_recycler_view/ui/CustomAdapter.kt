package com.example.basic_recycler_view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basic_recycler_view.model.data.remote.MovieReview
import com.example.basic_recycler_view.ui.MovieReviewEvents
import kotlinx.android.synthetic.main.list_item.view.imageItem
import kotlinx.android.synthetic.main.list_item.view.original_title
import kotlinx.android.synthetic.main.list_item.view.ratingBar

class CustomAdapter(private val listener: MovieReviewEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: ArrayList<MovieReview> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(com.example.basic_recycler_view.R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position], listener)
    }

    fun addAll(items: ArrayList<MovieReview>) {
        itemList.addAll(items)
        notifyItemRangeInserted(itemList.size - items.size, items.size)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var item: MovieReview

        fun bindItem(item: MovieReview, listener: MovieReviewEvents?) {
            this.item = item
            Glide.with(itemView)
                    .load("http://image.tmdb.org/t/p/w500" + item.posterPath)
                    .centerCrop()
                    .override(1000, 1000)
                    .into(itemView.imageItem)
            itemView.original_title.text = item.originalTitle
            itemView.ratingBar.rating = (item.voteAverage?.toFloat() ?: 0f) / 2
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}
