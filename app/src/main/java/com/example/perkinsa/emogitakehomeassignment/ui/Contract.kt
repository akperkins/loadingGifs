package com.example.perkinsa.emogitakehomeassignment.ui

import com.example.perkinsa.emogitakehomeassignment.data.dto.ItemDto
import com.example.perkinsa.emogitakehomeassignment.models.Item
import com.example.perkinsa.emogitakehomeassignment.ui.Contract.Presenter
import com.example.perkinsa.emogitakehomeassignment.ui.Contract.View

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
         * Called to indicate the [List] of [ItemDto] to display.
         */
        fun displayItems(items: List<Item>)
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

        /**
         * Called when we want to initiate a new load of data.
         */
        fun loadData(searchQuery: String = "")

        /**
         * Called to indicate that the user has changed their search term.
         */
        fun textChanged(it: CharSequence?)
    }
}