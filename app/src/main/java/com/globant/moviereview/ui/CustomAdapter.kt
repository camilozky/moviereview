package com.globant.moviereview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.globant.moviereview.R
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.model.calculateMovieRating
import kotlinx.android.synthetic.main.list_item.view.movieImage
import kotlinx.android.synthetic.main.list_item.view.original_title
import kotlinx.android.synthetic.main.list_item.view.ratingBar

/**
 * CustomAdapter
 *
 * Provides access to the data items, makes a View for each item in the data set
 *
 * @author david.mazo
 */

class CustomAdapter(private val listener: MovieReviewEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val movieReviewList: ArrayList<MovieReview> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return movieReviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movieReviewList[position], listener)
    }

    fun addAll(arrayListMovieReview: ArrayList<MovieReview>?) {
        arrayListMovieReview?.let { movieReviewList.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(movieReview: MovieReview, listener: MovieReviewEvents?) {
            Glide.with(itemView)
                    .load("http://image.tmdb.org/t/p/w500" + movieReview.posterPath)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.movieImage)
            itemView.original_title.text = movieReview.originalTitle
            itemView.ratingBar.rating = movieReview.voteAverage.toFloat() / calculateMovieRating()
            view.setOnClickListener {
                listener?.onItemClicked(movieReview)
            }
        }
    }
}
