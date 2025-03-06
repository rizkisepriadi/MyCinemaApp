package com.app.mycinemaapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.app.mycinemaapp.R
import com.app.mycinemaapp.core.domain.model.Movie
import com.app.mycinemaapp.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding
    private var detailMovie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)

        detailMovie?.let { movie ->
            detailMovieViewModel.getMovieById(movie.id).observe(this) { updatedMovie ->
                updatedMovie?.let { showDetailMovie(it) }
            }
        }
    }

    private fun showDetailMovie(movie: Movie) {
        detailMovie = movie
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.posterUrl}")
            .into(binding.thumbnailDetail)
        binding.tvTitleDetail.text = movie.title
        binding.tvCategoryDetail.text = movie.language
        binding.tvRatingDetail.rating = (movie.rating / 2).toFloat()
        binding.tvOverviewDetail.text = movie.overview

        var statusFavorite = movie.isFavorite
        setStatusFavorite(statusFavorite)

        binding.favoriteBtn.setOnClickListener {
            statusFavorite = !statusFavorite
            val updatedMovie = movie.copy(isFavorite = statusFavorite)
            detailMovieViewModel.setFavoriteMovie(updatedMovie, statusFavorite)
            setStatusFavorite(statusFavorite)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.favoriteBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.non_favorite_border_24
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}