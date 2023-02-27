package com.saad.homeflix.ui.modules.movies

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saad.homeflix.data.paging.LoaderAdapter
import com.saad.homeflix.databinding.FragmentMoviesBinding
import com.saad.homeflix.utils.ConnectivityStatus
import com.saad.homeflix.utils.progressDialog
import com.saad.homeflix.utils.showSnackBar
import com.saad.homeflix.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentMovies : Fragment() {

    //static
    private val TAG = "FragmentMovies"

    //progress dialog
    private lateinit var mProgressDialog: Dialog

    //binding
    private lateinit var mBinding: FragmentMoviesBinding

    //view model
    private val mMoviesVm by viewModels<VmMoviesList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init progress dialog
        mProgressDialog = requireContext().progressDialog()

        //setting adapter
        val adapter = AdapterMovies()
        mBinding.rvMovies.apply {
            this.setHasFixedSize(true)
            this.adapter = adapter
//                .withLoadStateHeaderAndFooter(
//                header = LoaderAdapter(),
//                footer = LoaderAdapter()
//            )
        }

        //observing live data for network connectivity
        val connection = context?.let { ConnectivityStatus(it) }
        connection?.observe(viewLifecycleOwner){
            isConnected ->
            if (isConnected){
                Log.e(TAG, "onViewCreated: true" )
            }else{
                Log.e(TAG, "onViewCreated: false" )
            }
        }

        //observing live data
        mMoviesVm.moviesLiveData.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        //showing progress dialog according to paging adapter load state
        lifecycleScope.launch {
            adapter.addLoadStateListener { loadState ->
                when (val state = loadState.source.refresh){
                    is LoadState.NotLoading -> {
                        mProgressDialog.dismiss()
                    }
                    LoadState.Loading -> mProgressDialog.show()
                    is LoadState.Error -> {
                        mProgressDialog.dismiss()
                        mBinding.root.showSnackBar(state.error.message.toString(),Snackbar.LENGTH_LONG)
                    }
                }
            }
        }


    }


}