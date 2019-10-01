package com.globant.moviereview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.globant.moviereview.model.local.db.MovieDatabase
import com.globant.moviereview.model.remote.MovieReview
import com.globant.moviereview.model.remote.voteRule
import kotlinx.android.synthetic.main.detail_item.*

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        var strUser: String? = intent.getStringExtra("idMovie")
        val movieDatabase = MovieDatabase.getDatabase(this@DetailMovieActivity)
        val movieReview: MovieReview? = strUser?.toInt().let { movieDatabase.getMovieDAO().getMovieDetail(it) }
        movieReview?.let { inflateView(it) }
    }

    private fun inflateView(movieReview: MovieReview) {
        textView_movie_title.text = movieReview.title
        textView_title_original.text = movieReview.originalTitle
        with((movieReview.voteAverage?.toFloat() ?: 0f) / voteRule) {
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
