package com.app.mycinemaapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mycinemaapp.R
import com.app.mycinemaapp.core.ui.MovieAdapter
import com.app.mycinemaapp.detail.DetailMovieActivity
import com.app.mycinemaapp.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.title_favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)

        getMovieData()
    }

    private fun getMovieData() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this) { dataMovie ->
            movieAdapter.submitList(dataMovie)
            binding.viewEmpty.root.visibility =
                if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
