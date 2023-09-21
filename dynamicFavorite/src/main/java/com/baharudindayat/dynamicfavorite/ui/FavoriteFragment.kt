package com.baharudindayat.dynamicfavorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.baharudindayat.dynamicfavorite.databinding.FragmentFavoriteBinding
import com.baharudindayat.dynamicfavorite.di.DaggerFavoriteComponent
import com.baharudindayat.dynamicfavorite.di.ViewModelFactory
import com.baharudindayat.nontonin.di.FavoriteModuleDependencies
import com.baharudindayat.nontonin.presentation.adapter.ListFilmAdapter
import com.baharudindayat.nontonin.presentation.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {


    @Inject
    lateinit var factory: ViewModelFactory
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val favoriteAdapter = ListFilmAdapter()
            favoriteAdapter.listener = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            favoriteViewModel.movie.observe(viewLifecycleOwner){ movie ->
                favoriteAdapter.setData(movie)
                binding.ivEmpty.rootView.visibility =
                    if (movie.isNotEmpty()) View.GONE else View.VISIBLE
            }
            with(binding.rvFilm){
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}