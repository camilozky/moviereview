package com.example.basic_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.basic_recycler_view.adapters.AdapterEvents
import com.example.basic_recycler_view.adapters.CustomAdapter
import com.example.basic_recycler_view.model.Item
import com.example.basic_recycler_view.services.DataService
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), AdapterEvents {
    override fun onItemClicked(item: Item) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val items = DataService()
        recyclerView.adapter = CustomAdapter(items.getItemsList(applicationContext), this)
    }
}
