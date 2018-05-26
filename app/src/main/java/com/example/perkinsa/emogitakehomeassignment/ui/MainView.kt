package com.example.perkinsa.emogitakehomeassignment.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.presentation.Contract
import com.example.presentation.MainViewState

/**
 * This class is responsible for handling the view responsibilities of the main screen.
 */
class MainView : Contract.View {
    private var itemAdapter: ItemAdapter = ItemAdapter(emptyList())

    private var recyclerView: RecyclerView? = null

    override fun render(state: MainViewState) {
        itemAdapter.updateItems(state.items)
    }

    fun init(mainActivity: MainActivity) {
        val viewManager = LinearLayoutManager(mainActivity)

        recyclerView = mainActivity.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = viewManager
            adapter = itemAdapter
        }
    }
}