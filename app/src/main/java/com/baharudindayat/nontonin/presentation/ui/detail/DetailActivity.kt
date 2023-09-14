package com.baharudindayat.nontonin.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.nontonin.R
import com.baharudindayat.nontonin.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailFilmViewModel: DetailFilmViewModel by viewModels()
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        detailMovie?.let { setDetailContentFilm(it) }

    }
    private fun setDetailContentFilm(detailMovie: Movie){
        detailMovie.let {
            with(binding){
                tvDetailTitle.text = it.originalTitle
                tvRating.text= it.voteAverage.toString()
                tvReleaseDate.text = it.releaseDate
                tvDetailDescription.text = it.overview
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .into(ivDetailPoster)
                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)
                binding.favoriteButton.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailFilmViewModel.setFavoriteMovie(detailMovie,statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_bookmark_24))
        } else {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_bookmark_border_24))
        }
    }


    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}