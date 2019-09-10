package com.example.basic_recycler_view.services

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.example.basic_recycler_view.MainActivity
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.model.Item
import com.squareup.picasso.Picasso
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

class DataService {

    fun getItemsList(context: Context): ArrayList<Item> {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(JsonService::class.java)
        val call = service.getCurrentData(AppId)

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



    call.enqueue(
    object : Callback<JsonResponse> {
        override fun onResponse(call: Call<JsonResponse>, response: Response<JsonResponse>) {
            if (response.code() == 200) {
                val Response = response.body()!!
                val stringBuilder = "Date: " + Response.date + "\n" + "Title: " + Response.title + "\n" + "explanation: " + Response.explanation
                Picasso.get()
                        .load(Response.url)
                        .into(ImageView(10000))
            }
        }

        override fun onFailure(call: Call<JsonResponse>, t: Throwable) {
//                Data.text = t.message
        }
    })


    companion object {

        var BaseUrl = "https://api.nasa.gov/planetary/"
        var AppId = "4V7MxGaddZYLgLj71sYviLo6gxValkgJwNVfUBQl"
    }
}
