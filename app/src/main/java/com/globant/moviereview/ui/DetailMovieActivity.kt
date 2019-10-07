package com.globant.moviereview.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.globant.moviereview.R
import com.globant.moviereview.model.MovieDatabase.Companion.getDatabase
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.Constants.Companion.ID_MOVIE
import com.globant.moviereview.utils.returnFactorMovieRating
import kotlinx.android.synthetic.main.detail_item.imageView_movie_picture
import kotlinx.android.synthetic.main.detail_item.image_view_ic_star
import kotlinx.android.synthetic.main.detail_item.ratingBar_average
import kotlinx.android.synthetic.main.detail_item.textView_average
import kotlinx.android.synthetic.main.detail_item.textView_movie_title
import kotlinx.android.synthetic.main.detail_item.textView_summary
import kotlinx.android.synthetic.main.detail_item.textView_title_original
import kotlinx.android.synthetic.main.detail_item.textView_title_release_date


/**
 * DetailMovieActivity
 *
 * Show the detail of a selected movie in the recyclerView of the MainActivity
 *
 * @author juan.rendon
 */

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieReview = intent.extras?.getInt(ID_MOVIE)?.let { getDatabase(this@DetailMovieActivity).getMovieDAO().getMovieDetail(it) }
        movieReview?.let { inflateView(it) }
    }

    private fun inflateView(movieReview: MovieReview) {
        textView_movie_title.text = movieReview.title
        textView_title_original.text = movieReview.originalTitle
        with(movieReview.voteAverage.toFloat() / returnFactorMovieRating()) {
            ratingBar_average.rating = this
            textView_average.text = this.toString()
        }
        textView_title_release_date.text = movieReview.releaseDate
        textView_summary.text = movieReview.summary
        Glide.with(imageView_movie_picture)
                .load("http://image.tmdb.org/t/p/w500" + movieReview.posterPath)
                .centerCrop()
                .fitCenter()
                .override(1000, 1000)
                .into(imageView_movie_picture)
        Glide.with(image_view_ic_star)
                .load("http://image.tmdb.org/t/p/w500" + movieReview.backdropPath)
                .centerCrop()
                .fitCenter()
                .override(1000, 1000)
                .into(image_view_ic_star)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}
