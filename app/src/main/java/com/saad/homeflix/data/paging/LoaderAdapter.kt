package com.saad.homeflix.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saad.homeflix.databinding.ProgressDialogBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderVH>() {

    class LoaderVH(private val binding: ProgressDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.root.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderVH, loadState: LoadState) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderVH {
        val binding =
            ProgressDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoaderVH(binding)
    }

}