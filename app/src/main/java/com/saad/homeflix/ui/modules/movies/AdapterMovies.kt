package com.saad.homeflix.ui.modules.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.RvMoviesItemBinding
import com.saad.homeflix.utils.loadImage

class AdapterMovies : PagingDataAdapter<ResultsItem, AdapterMovies.MoviesVH>(COMPARATOR) {

    class MoviesVH(private val binding: RvMoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem) {
            //setting data
            item.posterPath?.let { binding.imgPoster.loadImage(item.posterPath) }
            binding.tvName.text = item.title
            binding.tvReleaseDate.text = item.releaseDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        val binding =
            RvMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesVH(binding)
    }

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem == newItem

        }
    }

}