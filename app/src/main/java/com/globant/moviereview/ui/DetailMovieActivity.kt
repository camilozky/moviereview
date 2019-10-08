package com.globant.moviereview.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.globant.moviereview.R
import com.globant.moviereview.model.MovieDatabase.Companion.getMovieDatabase
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.utils.Constants.Companion.ID_MOVIE
import com.globant.moviereview.utils.returnFactorMovieRating
import kotlinx.android.synthetic.main.detail_item.*


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
        intent.extras?.let { bundle ->
            val movieReview = bundle.getInt(ID_MOVIE).let { id ->
                getMovieDatabase(this@DetailMovieActivity).getMovieDAO().getMovieDetail(id)
            }
            loadMovieReview(movieReview)
        }
    }

    private fun loadMovieReview(movieReview: MovieReview) {
        text_view_movie_title.text = movieReview.title
        text_view_title_original.text = movieReview.originalTitle
        text_view_title_release_date.text = movieReview.releaseDate
        text_view_summary.text = movieReview.summary
        Glide.with(image_view_movie_picture)
                .load("http://image.tmdb.org/t/p/w500" + movieReview.posterPath)
                .centerCrop()
                .fitCenter()
                .override(1000, 1000)
                .into(image_view_movie_picture)
        Glide.with(image_view_ic_star)
                .load("http://image.tmdb.org/t/p/w500" + movieReview.backdropPath)
                .centerCrop()
                .fitCenter()
                .override(1000, 1000)
                .into(image_view_ic_star)
        with(movieReview.voteAverage.toFloat() / returnFactorMovieRating()) {
            rating_bar_average.rating = this
            text_view_average.text = this.toString()
        }
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

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DetailMovieActivity::class.java)
        }
    }
}
