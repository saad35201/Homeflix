package com.saad.homeflix.views.modules.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.FragmentDetailBinding
import com.saad.homeflix.utils.loadImage
import com.saad.homeflix.views.modules.favorites.VmFavorites
import com.saad.homeflix.views.modules.movies.VmMovies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    //binding
    private lateinit var mBinding: FragmentDetailBinding
    //Nav Args
    private val mArgs: FragmentDetailArgs? by navArgs()
    //view model
    private val mFavoritesVm by viewModels<VmFavorites>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentDetailBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting argument
        mArgs?.let {
            initView(it.movieObj)
        }

        //click listener
        mBinding.imgFav.setOnClickListener {
            if (mArgs!!.movieObj.isLiked){
                lifecycleScope.launch {
                    mArgs!!.movieObj.id?.let {
                        mFavoritesVm.disLikeMovie(it)
                        Toast.makeText(requireContext(),"Disliked",Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                lifecycleScope.launch {
                    mArgs!!.movieObj.id?.let {
                        mFavoritesVm.addMovie(mArgs!!.movieObj)
                        mFavoritesVm.likeMovie(it)
                        Toast.makeText(requireContext(),"Liked",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    private fun initView(movieObj: ResultsItem) {
        movieObj.backdropPath?.let { mBinding.imgMovieBanner.loadImage(it) }
        movieObj.posterPath?.let { mBinding.imgMoviePoster.loadImage(it) }
        mBinding.tvName.text = movieObj.title
        mBinding.tvReleaseDate.text = movieObj.releaseDate
        mBinding.tvOverviewValue.text = movieObj.overview
    }

}