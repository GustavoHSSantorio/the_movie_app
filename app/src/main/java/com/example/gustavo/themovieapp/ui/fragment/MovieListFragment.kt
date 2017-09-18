package com.example.gustavo.themovieapp.ui.fragment

import android.app.Fragment
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.ui.activity.BasicListActivity
import com.example.gustavo.themovieapp.ui.activity.MainActivity
import com.example.gustavo.themovieapp.ui.adapter.MovieListAdapter
import com.example.gustavo.themovieapp.util.Constants
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance( bundle : Bundle) : Fragment {
            val fragment = MovieListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_movie_list, container, false)

    override fun onResume() {
        super.onResume()
        forwardToMovieCall()
    }

    fun forwardToMovieCall(){
        when(arguments.get(Constants.MainConstants.LIST_TYPE)){
            Constants.MainConstants.TYPE_NOW_PLAYNG -> (activity as BasicListActivity).loadMoviesNowPlayng()
            Constants.MainConstants.TYPE_TOP_RATED -> (activity as BasicListActivity).loadMoviesPopular()
            Constants.MainConstants.TYPE_UPCOMING -> (activity as BasicListActivity).loadMoviesUpcoming()
            Constants.MainConstants.TYPE_SEARCH -> (activity as BasicListActivity).searchForMovies(arguments.get(SearchManager.QUERY) as String)
        }
    }

    fun setAdapter(adapter : MovieListAdapter){
        rv_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rv_list.adapter = adapter
    }

}