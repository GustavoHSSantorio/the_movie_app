package com.example.gustavo.themovieapp.ui.activity

import android.app.AlertDialog
import android.app.Fragment
import android.arch.lifecycle.Observer
import android.os.Bundle

import com.example.gustavo.themovieapp.R
import com.example.gustavo.themovieapp.model.Error
import com.example.gustavo.themovieapp.model.Movie
import com.example.gustavo.themovieapp.ui.adapter.MovieListAdapter
import com.example.gustavo.themovieapp.ui.fragment.MovieListFragment
import com.example.gustavo.themovieapp.util.Constants
import com.example.gustavo.themovieapp.vm.MainViewModel
import com.stepstone.apprating.AppRatingDialog
import com.stepstone.apprating.listener.RatingDialogListener
import java.util.*
import android.R.string.cancel
import android.content.DialogInterface



open class BasicListActivity : BasicActivity(MainViewModel::class.java), RatingDialogListener {

    var fragment: Fragment? = null

    fun loadMoviesNowPlayng(page : Int){
        (getViewModel() as MainViewModel).loadMoviesNowPlayng(page)
    }

    fun loadMoviesPopular(page : Int){
        (getViewModel() as MainViewModel).loadMoviesPopular(page)
    }

    fun loadMoviesUpcoming(page : Int){
        (getViewModel() as MainViewModel).loadMoviesUpcoming(page)
    }

    fun searchForMovies(query : String, page : Int){
        (getViewModel() as MainViewModel).searchForMovies(query, page)
    }

    fun observeMovies(){
        (getViewModel() as MainViewModel).movieList.observe(this, Observer<List<Movie>> { movies ->
            if(movies != null && movies.size > 0)
                (fragment as MovieListFragment).updateDataSet(movies)
            else
                (fragment as MovieListFragment).showEmptyMessage()
        })
    }

    private fun getFragmentById(id: Int, bundle : Bundle): Fragment? {
        fragmentManager.executePendingTransactions()

        when (id) {
            R.id.nav_top_rated -> {
                bundle.putString(Constants.MainConstants.LIST_TYPE, Constants.MainConstants.TYPE_TOP_RATED)
                fragment = MovieListFragment.newInstance(bundle)
            }
            R.id.nav_now_playng -> {
                bundle.putString(Constants.MainConstants.LIST_TYPE, Constants.MainConstants.TYPE_NOW_PLAYNG)
                fragment = MovieListFragment.newInstance(bundle)
            }
            R.id.nav_upcoming -> {
                bundle.putString(Constants.MainConstants.LIST_TYPE, Constants.MainConstants.TYPE_UPCOMING)
                fragment = MovieListFragment.newInstance(bundle)
            }
            0 -> {
                bundle.putString(Constants.MainConstants.LIST_TYPE, Constants.MainConstants.TYPE_SEARCH)
                fragment = MovieListFragment.newInstance(bundle)
            }
        }

        return fragment
    }

    private fun getFragmentTagById(id: Int): String? {
        fragmentManager.executePendingTransactions()
        var fragment: String? = null

        when (id) {
            R.id.nav_top_rated -> fragment = Constants.MainConstants.TYPE_TOP_RATED
            R.id.nav_now_playng -> fragment = Constants.MainConstants.TYPE_NOW_PLAYNG
            R.id.nav_upcoming -> fragment = Constants.MainConstants.TYPE_UPCOMING
            0 -> fragment = Constants.MainConstants.TYPE_SEARCH
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

    fun navigate(id: Int, bundle: Bundle) {
        val tag = getFragmentTagById(id)
//        if (isFragmentInBackstack(tag)) {
//            fragmentManager.popBackStackImmediate(tag, 0)
//        } else {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fl_content, getFragmentById(id, bundle), tag)
            transaction.addToBackStack(tag)
            transaction.commit()
//        }
    }

    fun navigate(id: Int) {
        navigate(id, Bundle())
    }

    fun navigate(bundle: Bundle) {
        navigate(0, bundle)
    }

    fun showRateDialog(){
        AppRatingDialog.Builder()
                .setPositiveButtonText("Enviar")
                .setNegativeButtonText("Cancelar")
                .setNoteDescriptions(Arrays.asList("Muito ruim", "Ruim", "Normal", "Muito bom", "Excelente"))
                .setDefaultRating(2)
                .setTitle("Avalie este aplicativo")
                .setDescription("Escolha quantas estrelas este app merece")
                .setTitleTextColor(android.R.color.white)
                .setDescriptionTextColor(android.R.color.white)
                .setHint("Escreva um comentário de avaliação")
                .setHintTextColor(android.R.color.white)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(this).show()
    }

    override fun onNegativeButtonClicked() {

    }

    override fun onPositiveButtonClicked(rate: Int, comment: String) {

    }
}
