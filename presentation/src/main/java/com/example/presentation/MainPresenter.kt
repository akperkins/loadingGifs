package com.example.presentation

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This class is responsible for housing the business logic for the Main Screen.
 */
class MainPresenter(var view: Contract.View?, private val repository: DataStore) : Contract.Presenter {
    override fun init(currentSearchQuery: String) {
        val loadingIntentObservable: Observable<Result> =
                view!!.loadingIntent().flatMap {
                    repository.loadItems()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .map { items ->
                                currentItems = items
                        if (items.isEmpty()) {
                            Result.LoadingEmpty
                        } else {
                            Result.LoadingComplete(displayListOfTermsForQuery(currentSearchQuery))
                        }
                    }
                }.onErrorReturn { Result.LoadingError(it) }

        val editTextChangedObservable: Observable<Result> =
                view!!.textChanged().flatMap {
                    Observable.just(Result.DisplayQueryToUser(displayListOfTermsForQuery(it)))
                }

        subscribetoStates(Observable.merge(loadingIntentObservable, editTextChangedObservable))
    }

    fun subscribetoStates(allResultObservable: Observable<Result>): Disposable {
        // the scan function provides the previous state
        return allResultObservable.scan(MainViewState(items = emptyList(), loadingError = false)) { previousState, result ->
            reducer(previousState, result)
        }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    state -> view!!.render(state)
                }
    }

    fun reducer(previousState: MainViewState, result: Result): MainViewState {
        return when(result){
            is Result.LoadingError -> previousState.copy(loadingError = true)
            is Result.LoadingComplete -> previousState.copy(loadingError =  false,
                    items = result.items)
            Result.LoadingEmpty -> previousState.copy(loadingError = false,
                    items = emptyList())
            is Result.DisplayQueryToUser -> previousState.copy(items = result.items,
                    loadingError = false)
        }
    }

    var currentItems = emptyList<Item>()

    private fun displayListOfTermsForQuery(searchQuery: CharSequence?) : List<Item> {
        val queryText = searchQuery.toString().toLowerCase()
        val query = queryText.split(" ")
        return if (queryText.isBlank() || query.isEmpty()) {
            currentItems
        } else {
            val filteredList = currentItems.filter { it.hasTag(query) }
            filteredList
        }
    }

    override fun unbind() {
        view = null
    }
}