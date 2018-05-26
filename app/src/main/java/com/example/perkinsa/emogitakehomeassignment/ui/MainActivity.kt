package com.example.perkinsa.emogitakehomeassignment.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.data.AssetFilesRepository
import com.example.data.AssetsReader
import com.example.perkinsa.emogitakehomeassignment.R
import com.example.presentation.Contract
import com.example.presentation.MainPresenter

/**
 * This [AppCompatActivity] is responsible for being the Main Entry of the application and the
 * view of the main screen.
 */
class MainActivity : AppCompatActivity() {

    private var preseneter: Contract.Presenter? = null

    private var currentSearchQuery: String = ""

    lateinit var view: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository = AssetFilesRepository(AssetsReader(applicationContext))
        view = MainView()
        preseneter = MainPresenter(view, repository)
        view.init(this)

        savedInstanceState?.let{
            currentSearchQuery = it.getString(CURRENT_SEARCH_KEY, "")
        }
    }

    override fun onStart() {
        super.onStart()
        preseneter?.init(currentSearchQuery)
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

    companion object {
        const val CURRENT_SEARCH_KEY = "currentSearchKey"
    }
}