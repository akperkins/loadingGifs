package com.example.perkinsa.emogitakehomeassignment.data

import com.example.perkinsa.emogitakehomeassignment.data.dto.ItemDto
import com.example.perkinsa.emogitakehomeassignment.models.Item
import io.reactivex.Observable

/**
 * Implementors are responsible for being a data source for a [List] of [ItemDto]s.
 */
interface Repository {
    fun loadItems(): Observable<List<Item>>
}