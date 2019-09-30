package com.globant.moviereview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.globant.moviereview.model.remote.MovieReview
import com.globant.moviereview.model.remote.voteRule
import kotlinx.android.synthetic.main.detail_item.imageView_movie_picture
import kotlinx.android.synthetic.main.detail_item.image_view_ic_star
import kotlinx.android.synthetic.main.detail_item.ratingBar_average
import kotlinx.android.synthetic.main.detail_item.textView_average
import kotlinx.android.synthetic.main.detail_item.textView_movie_title
import kotlinx.android.synthetic.main.detail_item.textView_overview
import kotlinx.android.synthetic.main.detail_item.textView_title_original
import kotlinx.android.synthetic.main.detail_item.textView_title_release_date

class DetailMovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_item)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val movieReview: MovieReview = intent.getParcelableExtra("object_recycler_view")
        inflateView(movieReview)
    }

    private fun inflateView(movieReview: MovieReview) {
        textView_movie_title.text = movieReview.title
        textView_title_original.text = movieReview.originalTitle
        with((movieReview.voteAverage?.toFloat() ?: 0f) / voteRule) {
            ratingBar_average.rating = this
            textView_average.text = this.toString()
        }
        textView_title_release_date.text = movieReview.releaseDate
        textView_overview.text=movieReview.summary
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
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}
