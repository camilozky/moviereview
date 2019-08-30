package com.example.basic_recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.basic_recycler_view.adapters.CustomAdapter
import com.example.basic_recycler_view.model.Item
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), CustomAdapter.AdapterEvents {
    override fun onItemClicked(item: Item) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val items = ArrayList<Item>()
        items.add(Item(1, "Title1", "subTitle1", ""))
        items.add(Item(2, "Title2", "subTitle2", ""))
        items.add(Item(3, "Title3", "subTitle3", ""))
        items.add(Item(4, "Title4", "subTitle4", ""))
        items.add(Item(5, "Title5", "subTitle5", ""))
        items.add(Item(5, "Title6", "subTitle6", ""))
        val adapter = CustomAdapter(items, this)
        recyclerView.adapter = adapter

    }
}
