package com.baharudindayat.nontonin.presentation.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.nontonin.R
import com.baharudindayat.nontonin.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailFilmViewModel: DetailFilmViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailTourismData = arguments?.getParcelable<Movie>(EXTRA_DATA)
        if (detailTourismData != null){
            setDetailContentFilm(detailTourismData)
        }
    }

    private fun setDetailContentFilm(detailMovie: Movie){
        detailMovie.let {
            with(binding){
                tvDetailTitle.text = it.originalTitle
                tvRating.text= it.voteAverage.toString()
                tvReleaseDate.text = it.releaseDate
                tvDetailDescription.text = it.overview
                Glide.with(requireActivity())
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
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_bookmark_24))
        } else {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_bookmark_border_24))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}