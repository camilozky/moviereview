package com.globant.moviereview

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
import com.globant.moviereview.model.local.db.MovieDatabase
import com.globant.moviereview.model.remote.MovieReview
import com.globant.moviereview.repository.MovieRepository
import com.globant.moviereview.ui.CustomAdapter
import com.globant.moviereview.ui.MovieReviewEvents
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MovieReviewEvents, MovieRepository.ResponseInterface {
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

    override fun sendResponse(arrayListMovieReview: ArrayList<MovieReview>?) {
        arrayListMovieReview?.let {
            customAdapter.addAll(it)
            val movieDatabase = MovieDatabase.getDatabase(this@MainActivity)
            movieDatabase.getMovieDAO().insertMovie(arrayListMovieReview)
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
        val bundle = Bundle().apply {
            putParcelable("object_recycler_view", movieReview)
        }
        val intent = Intent(this, DetailMovieActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customAdapter = CustomAdapter(this)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        gridLayoutManager = GridLayoutManager(this, 2)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = customAdapter
        movieRepository = MovieRepository(this)
        movieRepository.getData()
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!movieRepository.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestDataFromMovieRepository()
                }
            }
        })
    }

    private fun requestDataFromMovieRepository() {
        try {
            movieRepository.getData()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val LINEAR_LAYOUT: Int = 1
        const val GRILL_LAYOUT: Int = 2
        const val STAGGERED_LAYOUT: Int = 3
    }
}
