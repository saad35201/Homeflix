package com.saad.homeflix.ui.modules.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.saad.homeflix.R
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.FragmentDetailBinding
import com.saad.homeflix.utils.loadImage


class FragmentDetail : Fragment() {

    //binding
    private lateinit var mBinding: FragmentDetailBinding
    //Nav Args
    private val mArgs: FragmentDetailArgs? by navArgs()

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

    }

    private fun initView(movieObj: ResultsItem) {
        movieObj.backdropPath?.let { mBinding.imgMovieBanner.loadImage(it) }
        movieObj.posterPath?.let { mBinding.imgMoviePoster.loadImage(it) }
        mBinding.tvName.text = movieObj.title
        mBinding.tvReleaseDate.text = movieObj.releaseDate
        mBinding.tvOverviewValue.text = movieObj.overview
    }

}