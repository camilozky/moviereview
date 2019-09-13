package com.example.basic_recycler_view.services

import android.app.Activity
import com.example.basic_recycler_view.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar


/**
 * DataService provides all data related to items
 *
 * @author david.mazo
 */

class DataSource(listeningActivity: Activity) {

    interface ResponseInterface {
        fun sendResponse(response: Item)
    }

    var isLoadingData: Boolean = false
    private val responseListener: ResponseInterface
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val calendar: Calendar = Calendar.getInstance()
    private val retrofit: Retrofit
    private val service: ApiService

    init {
        responseListener = listeningActivity as ResponseInterface
        retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ApiService::class.java)
    }

    fun getData() {
        val date = dateFormat.format(calendar.time)
        val call = service.getCurrentData(AppId)
        isLoadingData = true

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.code() == 200) {
                    itemResponse(response)
                }
                isLoadingData = false
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
                isLoadingData = false
            }
        })
    }

    private fun itemResponse(apiResponse: Response<ApiResponse>?) {

        apiResponse?.body()?.let { response ->
            val item = Item(0, response.popularity, response.vote_count, response.video, response.poster_path, response.adult, response
                    .backdrop_path, response.original_language, response.original_title, response.genre_ids, response.title, response.vote_average,
                    response.overview, response.release_date)
            responseListener.sendResponse(item)
        }
    }

    companion object {
        var BaseUrl = "https://api.themoviedb.org/3/"
        var AppId = "99ac1d44af506e889c0cb61a2ef3fa22"
        var Language = "en-US"
    }
}
