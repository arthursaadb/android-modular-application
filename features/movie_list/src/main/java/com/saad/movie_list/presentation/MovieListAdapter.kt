package com.saad.movie_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.saad.core.network.responses.MovieResponse
import com.saad.movie_list.databinding.MovieListItemBinding

class MovieListAdapter :
    PagingDataAdapter<MovieResponse, MovieListAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MovieViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class MovieViewHolder(
        private val binding: MovieListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(movieResponse: MovieResponse) {
            showTitle(movieResponse.title)
            showRating(movieResponse.voteAverage)
            showMovieImage(movieResponse.movieUrl)
        }

        private fun showTitle(title: String?) {
            binding.tvTitle.text = title
        }

        private fun showRating(rating: Double?){
            binding.tvVoteAverage.text = rating.toString()
        }

        private fun showMovieImage(icon: String?){
            binding.ivIcon.load(icon)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieResponse>() {
            override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieResponse,
                newItem: MovieResponse
            ): Boolean =
                oldItem == newItem
        }
    }
}