package com.example.basic_recycler_view.interfaces

import com.example.basic_recycler_view.services.MovieReview

interface AdapterEvents {
    fun onItemClicked(item: MovieReview)
}
