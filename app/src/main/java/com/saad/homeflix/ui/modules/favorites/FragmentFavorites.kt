package com.saad.homeflix.ui.modules.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.FragmentDetailBinding
import com.saad.homeflix.databinding.FragmentFavoritesBinding
import com.saad.homeflix.ui.modules.detail.FragmentDetailArgs
import com.saad.homeflix.utils.TAG
import com.saad.homeflix.utils.loadImage


class FragmentFavorites : Fragment() {

    //binding
    private lateinit var mBinding: FragmentFavoritesBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}