package com.example.perkinsa.emogitakehomeassignment.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.presentation.Contract
import com.example.presentation.MainViewState
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

/**
 * This class is responsible for handling the view responsibilities of the main screen.
 */
class MainView : Contract.View {
    override fun loadingIntent(): Observable<Boolean> = Observable.just(true)

    override fun textChanged(): Observable<CharSequence?> {
        return RxTextView.textChanges(editText)
    }

    private val itemAdapter: ItemAdapter = ItemAdapter(emptyList())

    private lateinit var recyclerView: RecyclerView

    private lateinit var editText:EditText

    override fun render(state: MainViewState) {
        itemAdapter.updateItems(state.items)
    }

    fun init(mainActivity: MainActivity) {
        val viewManager = LinearLayoutManager(mainActivity)

        recyclerView = mainActivity.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            layoutManager = viewManager
            adapter = itemAdapter
        }

        editText = mainActivity.findViewById(R.id.searchTerm)
    }
}