package com.saad.homeflix.ui.modules.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.saad.homeflix.R
import com.saad.homeflix.databinding.FragmentRegisterBinding


class FragmentRegister : Fragment() {

    //binding
    private lateinit var mBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentRegisterBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //btn register click listener
        mBinding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentRegister_to_fragmentMovies)
        }

    }

}