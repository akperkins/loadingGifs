package com.example.presentation

import io.reactivex.Observable

interface DataStore {
    fun loadItems(): Observable<List<Item>>
}