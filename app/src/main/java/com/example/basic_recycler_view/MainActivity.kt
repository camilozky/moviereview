package com.example.basic_recycler_view

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
import com.example.basic_recycler_view.model.data.db.MovieDatabase
import com.example.basic_recycler_view.model.data.remote.MovieReview
import com.example.basic_recycler_view.repository.MovieRepository
import com.example.basic_recycler_view.ui.MovieReviewEvents
import com.example.basic_recycler_view.ui.adapters.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MovieReviewEvents, MovieRepository.ResponseInterface {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var dataRequester: MovieRepository
    private lateinit var adapter: CustomAdapter
    private var layoutState: Int = LINEAR_LAYOUT


    private val lastVisibleItemPosition: Int
        get() = if (recyclerView.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }

    override fun sendResponse(response: ArrayList<MovieReview>?) {
        response?.let {
            adapter.addAll(it)
            val databaseMovie = MovieDatabase.getDatabase(this@MainActivity)
            databaseMovie.movieDAO().saveMovie(response)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dynamic, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (layoutState) {
            LINEAR_LAYOUT -> {
                recyclerView.layoutManager = gridLayoutManager
                layoutState = GRILL_LAYOUT
                item.icon = ContextCompat.getDrawable(this, R.drawable.icon_grilled)
            }
            GRILL_LAYOUT -> {
                recyclerView.layoutManager = staggeredGridLayoutManager
                layoutState = STAGGERED_LAYOUT
                item.icon = ContextCompat.getDrawable(this, R.drawable.icon_staggered)
            }
            STAGGERED_LAYOUT -> {
                recyclerView.layoutManager = linearLayoutManager
                layoutState = LINEAR_LAYOUT
                item.icon = ContextCompat.getDrawable(this, R.drawable.icon_linear)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(item: MovieReview) {
        val bundle = Bundle().apply {
            putParcelable("object_recycler_view", item)
        }
        val intent = Intent(this, DetailItemActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CustomAdapter(this)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        gridLayoutManager = GridLayoutManager(this, 2)
        staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataRequester = MovieRepository(this)
        dataRequester.getData()
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!dataRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestDataFromDataSource()
                }
            }
        })
    }

    private fun requestDataFromDataSource() {
        try {
            dataRequester.getData()
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
