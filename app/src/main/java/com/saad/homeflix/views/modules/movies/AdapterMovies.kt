package com.saad.homeflix.views.modules.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saad.homeflix.data.models.ResultsItem
import com.saad.homeflix.databinding.RvMoviesItemBinding
import com.saad.homeflix.utils.loadImage

class AdapterMovies(private val movies: List<ResultsItem>,private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<AdapterMovies.MoviesVH>() {

    class MoviesVH(private val binding: RvMoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem, itemClickListener: ItemClickListener) {
            //setting data
            item.posterPath?.let { binding.imgPoster.loadImage(item.posterPath) }
            binding.tvName.text = item.title
            binding.tvReleaseDate.text = item.releaseDate

            //invoking item click
            itemView.setOnClickListener {
                itemClickListener.onItemClicked(item)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesVH {
        val binding =
            RvMoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesVH(binding)
    }

    override fun onBindViewHolder(holder: MoviesVH, position: Int) {
        val item = movies[position]
        holder.bind(item,itemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}