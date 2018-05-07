package com.example.perkinsa.emogitakehomeassignment.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.perkinsa.emogitakehomeassignment.R

/**
 * Used to easily load images (or Gifs) via Glide by providing a simple extension function.
 */
fun ImageView.loadImage(url: String?) {
    val options = RequestOptions()
            .error(R.drawable.ic_error_black)

    Glide.with(this.context)
            .load(url)
            .apply(options)
            .into(this)
}