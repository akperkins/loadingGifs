package com.example.data

import com.example.presentation.Item
import io.reactivex.Observable

/**
 * Implementors are responsible for being a data source for a [List] of [ItemDto]s.
 */
interface Repository {
    fun loadItems(): Observable<List<Item>>
}