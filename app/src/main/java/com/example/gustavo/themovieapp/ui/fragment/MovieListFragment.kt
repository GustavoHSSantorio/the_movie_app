package com.example.gustavo.themovieapp.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.ui.activity.MainActivity
import com.example.gustavo.themovieapp.ui.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by Gustavo on 16/09/17.
 */
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() : Fragment = MovieListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_movie_list, container, false)

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).loadMovies()
    }

    fun setAdapter(adapter : MovieListAdapter){
        rv_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        rv_list.adapter = adapter
    }

}