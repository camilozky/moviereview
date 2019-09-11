package com.example.basic_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.adapters.CustomAdapter
import com.example.basic_recycler_view.model.Item
import com.example.basic_recycler_view.services.DataService
import kotlinx.android.synthetic.main.activity_main.recyclerView
import java.io.IOException

class MainActivity : AppCompatActivity(), AdapterEvents, DataService.ResponseInterface {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var imageRequester: DataService
    private lateinit var adapter: CustomAdapter

    private val lastVisibleItemPosition: Int
        get() = if (recyclerView.layoutManager == linearLayoutManager) {
            linearLayoutManager.findLastVisibleItemPosition()
        } else {
            gridLayoutManager.findLastVisibleItemPosition()
        }

    override fun sendResponse(response: Item) {
        adapter.addItem(response)
    }

    override fun onItemClicked(item: Item) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CustomAdapter(this)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        gridLayoutManager = GridLayoutManager(this, 2)
        imageRequester = DataService(this)
        imageRequester.getData(true)
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
            imageRequester.getData(false)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
