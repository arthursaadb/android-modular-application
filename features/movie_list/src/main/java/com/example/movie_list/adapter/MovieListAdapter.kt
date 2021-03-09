package com.example.movie_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_list.databinding.MovieListItemBinding
import com.saad.core.network.responses.MovieResponse

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

    inner class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(movieResponse: MovieResponse) {

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