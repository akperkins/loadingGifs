package com.example.perkinsa.emogitakehomeassignment.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.data.AssetFilesRepository
import com.example.data.AssetsReader
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.presentation.Contract
import com.example.presentation.Item
import com.example.presentation.MainPresenter
import com.jakewharton.rxbinding2.widget.RxTextView

/**
 * This [AppCompatActivity] is responsible for being the Main Entry of the application and the
 * view of the main screen.
 */
class MainActivity : AppCompatActivity(), Contract.View {

    private var preseneter: Contract.Presenter? = null

    private var recyclerView: RecyclerView? = null

    private var itemAdapter: ItemAdapter = ItemAdapter(emptyList())

    private var currentSearchQuery: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = AssetFilesRepository(AssetsReader(applicationContext))
        preseneter = MainPresenter(this, repository)

        val viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = viewManager
            adapter = itemAdapter
        }

        savedInstanceState?.let{
            currentSearchQuery = it.getString(CURRENT_SEARCH_KEY, "")
        }
    }

    override fun onStart() {
        super.onStart()
        preseneter?.loadData(currentSearchQuery)
        RxTextView.textChanges(findViewById(R.id.searchTerm))
                .subscribe {
                    currentSearchQuery = it.toString()
                    preseneter?.textChanged(it)
                }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(CURRENT_SEARCH_KEY, currentSearchQuery)
    }

    override fun onDestroy() {
        super.onDestroy()
        preseneter?.let {
            it.unbind()
            preseneter = null
        }
    }

    override fun displayItems(items: List<Item>) {
        itemAdapter.updateItems(items)
    }

    companion object {
        const val CURRENT_SEARCH_KEY = "currentSearchKey"
    }
}