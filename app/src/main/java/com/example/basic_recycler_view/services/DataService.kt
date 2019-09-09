package com.example.basic_recycler_view.services

import android.content.Context
import android.content.Intent
import com.example.basic_recycler_view.MainActivity
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.model.Item

/**
 * DataService provides all data related to items
 *
 * @author david.mazo
 */

class DataService {

    fun getItemsList(context: Context): ArrayList<Item> {
        val items = ArrayList<Item>()
        val intentMainActivity = Intent(context, MainActivity::class.java)
        items.add(Item(1, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        items.add(Item(2, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        items.add(Item(3, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        items.add(Item(4, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        items.add(Item(5, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        items.add(Item(6, "Title1", "subtitle", R.drawable.download, intentMainActivity))
        return items
    }

}
