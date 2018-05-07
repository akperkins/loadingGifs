package com.example.perkinsa.emogitakehomeassignment.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.perkinsa.emogitakehomeassignment.models.Item
import com.example.perkinsa.emogitakehomeassignment.ui.extensions.loadImage

/**
 * This [AppCompatActivity] is responsible for showing the detail view for any
 * particular [Item] that the user clicked.
 */
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val item = intent.getSerializableExtra(ITEM_KEY) as Item
        findViewById<ImageView>(R.id.imageView).loadImage(item.fullScreenUrl)
    }

    companion object {
        const val ITEM_KEY = "item"
    }
}
