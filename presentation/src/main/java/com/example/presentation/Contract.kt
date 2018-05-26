package com.example.presentation

import com.example.presentation.Contract.Presenter
import com.example.presentation.Contract.View
import io.reactivex.Observable

/**
 * This interface is just responsible for housing the [View] and the [Presenter] interfaces in one
 * location, it is not meant to be implemented.
  */
interface Contract {
    /**
     * Any class that implements this interface is responsible for handling the UI responsibilities
     * of the main screen.
     */
    interface View {
        /**
         * Called to indicate the [List] of [Item] to display.
         */
        fun render(state: MainViewState)

        /**
         * Called when we want to initiate a new load of data.
         */
        fun loadingIntent(): Observable<Boolean>

        /**
         * Called to indicate that the user has changed their search term.
         */
        fun textChanged(): Observable<CharSequence?>
    }

    /**
     * Any class that implements this interface is responsible for handling the business logic of
     * the main screen.
     */
    interface Presenter {
        /**
         * This is used when this presenter is to no longer be used. Used to clean up any references
         * to avoid memory leaks.
         */
        fun unbind()

        fun init(currentSearchQuery: String)
    }
}