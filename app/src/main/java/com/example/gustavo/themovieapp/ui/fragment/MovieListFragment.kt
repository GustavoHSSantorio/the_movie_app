package com.example.gustavo.themovieapp.ui.fragment

import android.app.Fragment
import android.app.SearchManager
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.ui.activity.BasicListActivity
import com.example.gustavo.themovieapp.ui.adapter.MovieListAdapter
import com.example.gustavo.themovieapp.util.Constants
import kotlinx.android.synthetic.main.fragment_movie_list.*
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.ui.listener.InfiniteScrollListener
import com.example.gustavo.themovieapp.vm.MainViewModel


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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srl_refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        srl_refresh.setOnRefreshListener { ->
            ((activity as BasicListActivity).getViewModel() as MainViewModel).reinitNextPage()
            forwardToMovieCall()
        }
        initRv()
    }

    override fun onResume() {
        super.onResume()
        forwardToMovieCall()
    }

    fun forwardToMovieCall(){
        if(!srl_refresh.isRefreshing)
            material_progress_bar.visibility = View.VISIBLE

        val page = ((activity as BasicListActivity).getViewModel() as MainViewModel).getNextPage()
        when(arguments.get(Constants.MainConstants.LIST_TYPE)){
            Constants.MainConstants.TYPE_NOW_PLAYNG -> (activity as BasicListActivity).loadMoviesNowPlayng(page)
            Constants.MainConstants.TYPE_TOP_RATED -> (activity as BasicListActivity).loadMoviesPopular(page)
            Constants.MainConstants.TYPE_UPCOMING -> (activity as BasicListActivity).loadMoviesUpcoming(page)
            Constants.MainConstants.TYPE_SEARCH -> (activity as BasicListActivity).searchForMovies(arguments.get(SearchManager.QUERY) as String, page)
        }
    }

    fun initRv(){
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_list.addOnScrollListener(InfiniteScrollListener({ forwardToMovieCall() }, layoutManager))
        rv_list.layoutManager = layoutManager
        rv_list.adapter = MovieListAdapter(ArrayList<Movie>(), activity)
    }

    fun updateDataSet(newList : List<Movie>){
        tv_empty_result.visibility = View.GONE
        material_progress_bar.visibility = View.GONE
        srl_refresh.setRefreshing(false)
        (rv_list.adapter as MovieListAdapter).updateDataSet(newList)
    }

    fun showEmptyMessage(){
        material_progress_bar.visibility = View.GONE
        srl_refresh.setRefreshing(false)
        tv_empty_result.visibility = View.VISIBLE
    }
}