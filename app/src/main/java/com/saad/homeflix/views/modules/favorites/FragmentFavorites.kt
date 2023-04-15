package com.saad.homeflix.views.modules.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.FragmentFavoritesBinding
import com.saad.homeflix.utils.observe
import com.saad.homeflix.views.base.BaseFragment
import com.saad.homeflix.views.modules.movies.AdapterMovies
import com.saad.homeflix.views.modules.movies.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentFavorites : BaseFragment(), ItemClickListener {

    //binding
    private lateinit var mBinding: FragmentFavoritesBinding

    //view model
    private val mFavoritesVm by viewModels<VmFavorites>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            mFavoritesVm.getAllFavoriteMovies()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun observeViewModels() {
        observe(mFavoritesVm.moviesLiveData, ::bindListData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    private fun bindListData(results: List<ResultsItem?>?) {
        if (!results.isNullOrEmpty()) {
            mBinding.rvFavMovies.apply {
                adapter = AdapterMovies(results as List<ResultsItem>, this@FragmentFavorites)
            }
        }
        return
    }

    override fun onItemClicked(item: ResultsItem) {
        TODO("Not yet implemented")
    }


}