package com.example.perkinsa.emogitakehomeassignment.ui

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.perkinsa.emogitakehomeassignment.BuildConfig
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.perkinsa.emogitakehomeassignment.models.Item
import com.example.perkinsa.emogitakehomeassignment.ui.extensions.loadImage
import org.jetbrains.anko.startActivity

/**
 * This [RecyclerView.Adapter] is responsible for powering the [RecyclerView] that displays
 * the list of [Item] in [MainActivity].
 */
class ItemAdapter(private var items: List<Item>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemAdapter.ViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail, parent, false)
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.update(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun update(item: Item) {
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            imageView.loadImage(item.thumbnailUrl)

            view.findViewById<TextView>(R.id.textView).text = item.content_id

            if(BuildConfig.SHOW_TAGS) {
                val tagsTextView = view.findViewById<TextView>(R.id.tags)
                tagsTextView.visibility = View.VISIBLE
                tagsTextView.text = item.tags.toString()
            }

            view.setOnClickListener { (view.context as Activity).startActivity<DetailActivity>(DetailActivity.ITEM_KEY to item) }
        }
    }
}