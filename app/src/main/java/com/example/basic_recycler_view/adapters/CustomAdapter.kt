package com.example.basic_recycler_view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.model.Item
import kotlinx.android.synthetic.main.list_item.view.imageItem
import kotlinx.android.synthetic.main.list_item.view.subTitle
import kotlinx.android.synthetic.main.list_item.view.title

class CustomAdapter(private val itemList: ArrayList<Item>, private val listener: AdapterEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList[position], listener)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(item: Item, listener: AdapterEvents?) {
            itemView.imageItem.setImageResource(item.image)
            itemView.title.text = item.title
            itemView.subTitle.text = item.subTitle
            view.setOnClickListener {
                listener?.onItemClicked(item)
            }
        }
    }
}