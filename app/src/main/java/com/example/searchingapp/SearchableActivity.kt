package com.example.searchingapp

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log

class SearchableActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SearchableActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        Log.d(TAG, "onNewIntent")
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        // Verify the action and get the query
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestions(this, MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        Log.d(TAG, "doMySearch: query: $query")
    }
}