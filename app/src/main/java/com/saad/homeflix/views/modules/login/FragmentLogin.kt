package com.saad.homeflix.views.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saad.homeflix.R
import com.saad.homeflix.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLogin : Fragment() {

    //binding
    private lateinit var mBinding: FragmentLoginBinding
    //vm
    private val mLoginVm by viewModels<VmLogin>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //btn login click listener
        mBinding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMovies)
        }

        //tv register click listener
        mBinding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }

    }

}