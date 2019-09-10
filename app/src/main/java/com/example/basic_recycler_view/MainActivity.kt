package com.example.basic_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.adapters.CustomAdapter
import com.example.basic_recycler_view.model.Item
import com.example.basic_recycler_view.services.DataService
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), AdapterEvents, DataService.ResponseInterface {

    override fun onItemClicked(item: Item) {
    }

    private lateinit var recyclerViewData: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val items = DataService(this)
        items.getCurrentData()
        recyclerViewData = recyclerView
    }

    override fun sendResponse(response: ArrayList<Item>) {
        recyclerView.adapter = CustomAdapter(response, this)

    }

}
