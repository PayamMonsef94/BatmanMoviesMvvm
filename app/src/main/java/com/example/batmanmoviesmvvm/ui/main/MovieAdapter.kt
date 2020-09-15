package com.example.batmanmoviesmvvm.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanmoviesmvvm.R
import com.example.batmanmoviesmvvm.data.model.Movie
import com.example.batmanmoviesmvvm.databinding.ItemMovieBinding

class MovieAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Movie, MovieAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, item: Movie) {

            binding.viewModel = viewModel
            binding.movie = item
            binding.favorite = false
            binding.itemMovieIvFavorite.setOnClickListener {
                if (binding.favorite) {
                    binding.favorite = false
                    binding.itemMovieIvFavorite.setColorFilter(
                        ContextCompat.getColor(
                            it.context,
                            R.color.grey_background
                        ), android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    binding.favorite = true
                    binding.itemMovieIvFavorite.setColorFilter(
                        ContextCompat.getColor(
                            it.context,
                            R.color.red
                        ), android.graphics.PorterDuff.Mode.MULTIPLY
                    )
                }
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}