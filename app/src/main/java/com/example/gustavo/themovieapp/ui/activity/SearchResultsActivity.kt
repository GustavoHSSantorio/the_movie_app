package com.example.gustavo.themovieapp.ui.activity

import android.app.Activity
import android.app.ListActivity
import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.MenuItem

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.vm.MainViewModel
import kotlinx.android.synthetic.main.app_bar_main.*

class SearchResultsActivity : BasicListActivity(){

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_bar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        observeMovies()
        navigate(getIntent().extras)
    }

    override fun onNewIntent(intent : Intent) {
        setIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}