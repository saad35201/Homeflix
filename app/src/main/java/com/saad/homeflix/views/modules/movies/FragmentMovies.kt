package com.saad.homeflix.views.modules.movies

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saad.homeflix.data.models.ResponseMovies
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.FragmentMoviesBinding
import com.saad.homeflix.utils.NetworkResult
import com.saad.homeflix.utils.observe
import com.saad.homeflix.utils.progressDialog
import com.saad.homeflix.utils.showSnackBar
import com.saad.homeflix.views.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMovies : BaseFragment(), ItemClickListener {

    //binding
    private lateinit var mBinding: FragmentMoviesBinding

    //view model
    private val mMoviesVm by viewModels<VmMoviesList>()

    //progress dialog
    private lateinit var mProgressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //getting movies data
        mMoviesVm.getMovies()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun observeViewModels() {
        observe(mMoviesVm.moviesLiveData, ::handleMoviesList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //progress dialog
        mProgressDialog = requireContext().progressDialog()

        //query text change listener
        mBinding.svMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.equals("")) {
                    mMoviesVm.searchMovie(query!!)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        //search close btn click listener
        val closeBtn: View =
            mBinding.svMovies.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeBtn.setOnClickListener {
            mBinding.svMovies.setQuery("", false)
            mMoviesVm.getMovies()
        }

    }


    private fun handleMoviesList(status: NetworkResult<ResponseMovies>) {
        mProgressDialog.dismiss()
        when (status) {
            is NetworkResult.Success -> {
                status.data?.let {
                    bindListData(it.results)
                }
            }
            is NetworkResult.Error -> {
                status.message?.let { it1 -> mBinding.root.showSnackBar(it1, 3000) }
            }
            is NetworkResult.Loading -> {
                mProgressDialog.show()
            }
        }

    }

    private fun bindListData(results: List<ResultsItem?>?) {
        if (!results.isNullOrEmpty()) {
            mBinding.rvMovies.apply {
                adapter = AdapterMovies(results as List<ResultsItem>, this@FragmentMovies)
            }
        }
        return
    }

    override fun onItemClicked(item: ResultsItem) {
        val action = FragmentMoviesDirections.actionFragmentMoviesToFragmentDetail(item)
        findNavController().navigate(action)
    }


}