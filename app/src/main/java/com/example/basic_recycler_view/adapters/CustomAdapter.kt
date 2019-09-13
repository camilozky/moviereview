package com.example.basic_recycler_view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.Interface.AdapterEvents
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.imageItem
import kotlinx.android.synthetic.main.list_item.view.item_movie_genre
import kotlinx.android.synthetic.main.list_item.view.item_movie_rating
import kotlinx.android.synthetic.main.list_item.view.item_movie_release_date
import kotlinx.android.synthetic.main.list_item.view.item_movie_title

class CustomAdapter(private val listener: AdapterEvents) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val itemList: ArrayList<Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position], listener)
    }

    fun addItem(item: Item) {
        itemList.add(item)
        notifyItemInserted(itemList.size)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: Item, listener: AdapterEvents?) {
            Picasso.get()
                    .load("http://image.tmdb.org/t/p/w500" + item.poster_path)
                    .centerCrop()
                    .resize(1000, 1000)
                    .into(itemView.imageItem)
            itemView.item_movie_release_date.text = item.date
            itemView.item_movie_title.text = item.title
            itemView.item_movie_genre.text = item.explanation
            itemView.item_movie_rating.text = item.title
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}
