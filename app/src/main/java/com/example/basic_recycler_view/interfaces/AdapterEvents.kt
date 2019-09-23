package com.example.basic_recycler_view.interfaces

import com.example.basic_recycler_view.services.ApiMovie

interface AdapterEvents {
    fun onItemClicked(item: ApiMovie)
}
