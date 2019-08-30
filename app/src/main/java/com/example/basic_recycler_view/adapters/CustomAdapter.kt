package com.example.basic_recycler_view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_recycler_view.R
import com.example.basic_recycler_view.model.Item
import kotlinx.android.synthetic.main.list_item.view.img
import kotlinx.android.synthetic.main.list_item.view.subTitle
import kotlinx.android.synthetic.main.list_item.view.title

class CustomAdapter(private val itemList: ArrayList<Item>, private val listener: AdapterEvents) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var item: Item

        fun bindItems(item: Item) {
            this.item = item
            val resourceId = itemView.context.resources.getIdentifier(
                    item.image,
                    "drawable",
                    itemView.context.packageName
            )
            itemView.img.setImageResource(resourceId)
            itemView.title.text = item.title
            itemView.subTitle.text = item.subTitle
        }

        fun bindListener(listener: AdapterEvents?) {
            view.setOnClickListener {
                listener?.onItemClicked(this.item)
            }
        }
    }

    interface AdapterEvents {
        fun onItemClicked(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(itemList[position])
        holder.bindListener(listener)
    }

}
