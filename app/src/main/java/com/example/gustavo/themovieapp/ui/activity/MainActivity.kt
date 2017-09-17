package com.example.gustavo.themovieapp.ui.activity

import android.app.Fragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.ui.adapter.MovieListAdapter
import com.example.gustavo.themovieapp.ui.fragment.MovieListFragment
import com.example.gustavo.themovieapp.util.Constants
import com.example.gustavo.themovieapp.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BasicActivity(MainViewModel::class.java), NavigationView.OnNavigationItemSelectedListener{

    var fragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        observeMovies();
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        navigate(id)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun loadMovies(){
        (getViewModel() as MainViewModel).loadMovies(0)
    }

    fun observeMovies(){
        (getViewModel() as MainViewModel).movieList.observe(this, Observer<List<Movie>> { movies ->
            (fragment as MovieListFragment).setAdapter(MovieListAdapter(movies!!){
//                Implements onclick
            })
        })
    }

    private fun getFragmentById(id: Int): Fragment? {
        fragmentManager.executePendingTransactions()

        when (id) {
            R.id.nav_camera -> fragment = MovieListFragment.newInstance()
        }

        return fragment
    }

    private fun getFragmentTagById(id: Int): String? {
        fragmentManager.executePendingTransactions()
        var fragment: String? = null

        when (id) {
            R.id.nav_camera -> fragment = Constants.MainConstants.ROUTES_NAVIGATION_TAG
       }

        return fragment
    }

    private fun isFragmentInBackstack(fragmentTagName: String?): Boolean {
        for (entry in 0..fragmentManager.backStackEntryCount - 1) {
            if (fragmentTagName == fragmentManager.getBackStackEntryAt(entry).name) {
                return true
            }
        }
        return false
    }

    fun navigate(id: Int) {
        val tag = getFragmentTagById(id)
        if (isFragmentInBackstack(tag)) {
            fragmentManager.popBackStackImmediate(tag, 0)
        } else {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fl_content, getFragmentById(id), tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        }
    }
}
