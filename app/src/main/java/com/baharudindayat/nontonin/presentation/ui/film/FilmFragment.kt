package com.baharudindayat.nontonin.presentation.ui.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudindayat.core.data.Resource
import com.baharudindayat.nontonin.databinding.FragmentFilmBinding
import com.baharudindayat.nontonin.presentation.adapter.ListFilmAdapter
import com.baharudindayat.nontonin.presentation.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!
    private val filmViewModel: FilmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val filmAdapter = ListFilmAdapter()
            filmAdapter.listener = { selectedData ->

                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)

            }

            filmViewModel.movie.observe(viewLifecycleOwner){ movie ->
                if (movie != null){
                    when(movie){
                        is Resource.Loading -> {
                            binding.progressLoading.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressLoading.visibility = View.GONE
                            filmAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.progressLoading.visibility = View.GONE
                            binding.rvFilm.visibility = View.GONE
                            binding.rvFilm.visibility = View.VISIBLE
                        }
                    }
                }
            }
            with(binding.rvFilm){
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = filmAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}