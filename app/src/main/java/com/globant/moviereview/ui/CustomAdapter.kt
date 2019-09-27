package com.globant.moviereview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.globant.moviereview.R
import com.globant.moviereview.model.remote.MovieReview
import kotlinx.android.synthetic.main.list_item.view.*

class CustomAdapter(private val listener: MovieReviewEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: ArrayList<MovieReview> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position], listener)
    }

    fun addAll(items: ArrayList<MovieReview>) {
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var item: MovieReview

        fun bindItem(item: MovieReview, listener: MovieReviewEvents?) {
            this.item = item
            Glide.with(itemView)
                    .load("http://image.tmdb.org/t/p/w500" + item.posterPath)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.movieImage)
            itemView.original_title.text = item.originalTitle
            itemView.ratingBar.rating = (item.voteAverage?.toFloat() ?: 0f) / 2
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}
