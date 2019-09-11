package com.example.basic_recycler_view.services

import android.app.Activity
import com.example.basic_recycler_view.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * DataService provides all data related to items
 *
 * @author david.mazo
 */

class DataService(listeningActivity: Activity) {

    interface ResponseInterface {
        fun sendResponse(response: ArrayList<Item>)
    }

    private val responseListener: ResponseInterface

    init {
        responseListener = listeningActivity as ResponseInterface
    }

    fun getCurrentData() {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getCurrentData(AppId)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.code() == 200) {
                    itemResponse(response)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            }
        })
    }

    private fun itemResponse(apiResponse: Response<ApiResponse>?) {
        apiResponse?.body()?.let { response ->
            val item = Item(0, response.date, response.title, response.url)
            val items = ArrayList<Item>()
            items.add(item)
            responseListener.sendResponse(items)
        }
    }

    companion object {
        var BaseUrl = "https://api.nasa.gov/planetary/"
        var AppId = "4V7MxGaddZYLgLj71sYviLo6gxValkgJwNVfUBQl"
    }
}
