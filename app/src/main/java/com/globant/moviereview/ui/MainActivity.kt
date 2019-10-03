package com.globant.moviereview.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.globant.moviereview.R
import com.globant.moviereview.model.MovieReview
import com.globant.moviereview.repository.MovieRepository
import com.globant.moviereview.repository.ResponseInterface
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MovieReviewEvents, ResponseInterface {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var movieRepository: MovieRepository
    private lateinit var customAdapter: CustomAdapter
    private var layoutState: Int = LINEAR_LAYOUT
    private val lastVisibleItemPosition: Int
        get() = if (recyclerView.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customAdapter = CustomAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = customAdapter
        movieRepository = MovieRepository(this)
        movieRepository.getData(applicationContext)
        setRecyclerViewScrollListener()
    }

    override fun getListMovies(arrayListMovieReview: ArrayList<MovieReview>?) {
        arrayListMovieReview?.let {
            customAdapter.addAll(it)
        }
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
        val intent = Intent(this@MainActivity, DetailMovieActivity::class.java)
        intent.putExtra(ID_MOVIE, movieReview.id)
        startActivity(intent)
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    onRecyclerViewScroll()
                }
            }
        })
    }

    private fun onRecyclerViewScroll() {
        movieRepository.getData(applicationContext)
    }

    companion object {
        const val LINEAR_LAYOUT: Int = 1
        const val GRILL_LAYOUT: Int = 2
        const val STAGGERED_LAYOUT: Int = 3
        const val ID_MOVIE: String = "idMovie"
    }
}
