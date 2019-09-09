package com.example.basic_recycler_view.Interface

import com.example.basic_recycler_view.model.Item

interface AdapterEvents {
    fun onItemClicked(item: Item)
}
