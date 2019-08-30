package com.example.basic_recycler_view.adapters

import com.example.basic_recycler_view.model.Item

interface AdapterEvents {
    fun onItemClicked(item: Item)
}
