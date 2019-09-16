package com.example.basic_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.adapters.CustomAdapter
import com.example.basic_recycler_view.services.ApiMovie
import com.example.basic_recycler_view.services.DataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(), AdapterEvents, DataSource.ResponseInterface {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var imageRequester: DataSource
    private lateinit var adapter: CustomAdapter

    private val lastVisibleItemPosition: Int
        get() = if (recyclerView.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }

    override fun sendResponse(response: ArrayList<ApiMovie>?) {
        response?.let { adapter.addAll(it) }
    }

    override fun onItemClicked(item: ApiMovie) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CustomAdapter(this)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        gridLayoutManager = GridLayoutManager(this, 2)
        imageRequester = DataSource(this)
        imageRequester.getData()
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!imageRequester.isLoadingData && totalItemCount == lastVisibleItemPosition + 1) {
                    requestPhoto()
                }
            }
        })
    }

    private fun requestPhoto() {
        try {
            imageRequester.getData()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
