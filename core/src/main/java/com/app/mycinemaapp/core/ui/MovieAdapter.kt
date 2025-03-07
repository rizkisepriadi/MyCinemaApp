package com.app.mycinemaapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.mycinemaapp.core.databinding.ItemListMovieBinding
import com.app.mycinemaapp.core.domain.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieAdapter.ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${data.posterUrl}")
                .into(binding.thumbnail)
            binding.tvTitle.text = data.title
            binding.tvRating.rating = (data.rating / 2).toFloat()
            binding.tvDescription.text = if (data.overview.length > 150) {
                "${data.overview.take(150)}..."
            } else {
                data.overview
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
    }
}