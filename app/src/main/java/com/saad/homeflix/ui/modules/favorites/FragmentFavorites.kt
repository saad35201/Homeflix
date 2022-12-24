package com.saad.homeflix.ui.modules.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saad.homeflix.databinding.FragmentFavoritesBinding


class FragmentFavorites : Fragment() {

    //binding
    private lateinit var mBinding: FragmentFavoritesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return mBinding.root
    }


}