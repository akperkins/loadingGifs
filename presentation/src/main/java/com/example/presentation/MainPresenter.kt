package com.example.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This class is responsible for housing the business logic of the [MainActivity].
 */
class MainPresenter(var view: Contract.View?, val repository: DataStore) : Contract.Presenter {

    var subscription: Disposable? = null

    var currentItems = emptyList<Item>()

    override fun loadData(searchQuery: String) {
        subscription = repository.loadItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let {
                        currentItems = it
                        displayListOfTermsForQuery(searchQuery)
                    }
                })
    }

    override fun textChanged(it: CharSequence?) {
        displayListOfTermsForQuery(it)
    }

    private fun displayListOfTermsForQuery(searchQuery: CharSequence?) {
        val queryText = searchQuery.toString().toLowerCase()
        val query = queryText.split(" ")
        var itemsToDisplay = if (queryText.isBlank() || query.isEmpty()) {
            currentItems
        } else {
            val filteredList = currentItems.filter { it.hasTag(query) }
            filteredList
        }
        view?.displayItems(itemsToDisplay)
    }

    override fun unbind() {
        subscription?.dispose()
        view = null
    }
}