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

class DataService(listeningActivity: Activity) {

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

    fun getData(firstCall: Boolean) {
        if (!firstCall) {
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }

        val date = dateFormat.format(calendar.time)
        val call = service.getCurrentData(AppId, date)
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
            val item = Item(0, response.date, response.title, response.url)
            responseListener.sendResponse(item)
        }
    }

    companion object {
        var BaseUrl = "https://api.nasa.gov/planetary/"
        var AppId = "4V7MxGaddZYLgLj71sYviLo6gxValkgJwNVfUBQl"
    }
}
