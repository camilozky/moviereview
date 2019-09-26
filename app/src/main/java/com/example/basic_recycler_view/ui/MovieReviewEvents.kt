package com.example.basic_recycler_view.ui

import com.example.basic_recycler_view.model.remote.MovieReview

interface MovieReviewEvents {
    fun onItemClicked(item: MovieReview)
}
