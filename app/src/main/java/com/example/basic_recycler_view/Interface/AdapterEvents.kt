package com.example.basic_recycler_view.Interface

import com.example.basic_recycler_view.services.ApiMovie

interface AdapterEvents {
    fun onItemClicked(item: ApiMovie)
}
