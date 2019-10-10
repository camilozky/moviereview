package com.globant.moviereview.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.globant.moviereview.R
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.repository.MovieRepository
import com.globant.moviereview.utils.Constants
import com.globant.moviereview.utils.Constants.Companion.GRILL_LAYOUT
import com.globant.moviereview.utils.Constants.Companion.ID_MOVIE
import com.globant.moviereview.utils.Constants.Companion.LINEAR_LAYOUT
import com.globant.moviereview.utils.Constants.Companion.STAGGERED_LAYOUT
import com.globant.moviereview.utils.MovieReviewEvents
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), MovieReviewEvents {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieReviewListAdapter: MovieReviewListAdapter
    private var layoutState: Int = LINEAR_LAYOUT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        movieReviewListAdapter = MovieReviewListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = movieReviewListAdapter
        movieRepository = MovieRepository(this)
        movieReviewListAdapter.addAll(movieRepository.requestMovieReviewList())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dynamic, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (layoutState) {
            LINEAR_LAYOUT -> {
                recyclerView.layoutManager = gridLayoutManager
                layoutState = GRILL_LAYOUT
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.icon_grilled)
            }
            GRILL_LAYOUT -> {
                recyclerView.layoutManager = staggeredGridLayoutManager
                layoutState = STAGGERED_LAYOUT
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.icon_staggered)
            }
            STAGGERED_LAYOUT -> {
                recyclerView.layoutManager = linearLayoutManager
                layoutState = LINEAR_LAYOUT
                menuItem.icon = ContextCompat.getDrawable(this, R.drawable.icon_linear)
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onItemClicked(movieReview: MovieReview) {
        val intent: Intent = DetailMovieActivity.createIntent(this)
        intent.putExtra(ID_MOVIE, movieReview.id)
        startActivity(intent)
    }

    companion object {
        fun getFactorMovieReviewRating(): Int {
            return Constants.VOTE_MAX / Constants.RATING_MAX
        }
    }
}
