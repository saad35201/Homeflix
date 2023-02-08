package com.saad.homeflix.ui.modules.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.saad.homeflix.data.paging.LoaderAdapter
import com.saad.homeflix.databinding.FragmentMoviesBinding
import com.saad.homeflix.utils.ConnectivityStatus
import com.saad.homeflix.utils.progressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMovies : Fragment() {

    //static
    private val TAG = "FragmentMovies"

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

        //setting adapter
        val adapter = AdapterMovies()
        mBinding.rvMovies.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        //test
        val connection = context?.let { ConnectivityStatus(it) }
        connection?.observe(viewLifecycleOwner){
            isConnected ->
            if (isConnected){
                Log.e(TAG, "onViewCreated: true" )
            }else{
                Log.e(TAG, "onViewCreated: false" )
            }
        }

        //showing progress dialog
        val dialog = context?.progressDialog()
        dialog?.show()

        //observing live data
        mMoviesVm.moviesLiveData.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
            dialog?.dismiss()
        }

    }


}