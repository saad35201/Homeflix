package com.saad.homeflix.ui.modules.movies

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.saad.homeflix.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMovies : Fragment() {

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

        val adapter = AdapterMovies()
        mBinding.rvMovies.adapter = adapter

        mMoviesVm.moviesLiveData.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

    }


}