package com.example.perkinsa.emogitakehomeassignment.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.presentation.Contract
import com.example.presentation.Item

/**
 * This class is responsible for handling the view responsibilities of the main screen.
 */
class MainView : Contract.View {
    private var itemAdapter: ItemAdapter = ItemAdapter(emptyList())

    private var recyclerView: RecyclerView? = null

    override fun displayItems(items: List<Item>) {
        itemAdapter.updateItems(items)
    }

    fun init(mainActivity: MainActivity) {
        val viewManager = LinearLayoutManager(mainActivity)

        recyclerView = mainActivity.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = viewManager
            adapter = itemAdapter
        }
    }
}