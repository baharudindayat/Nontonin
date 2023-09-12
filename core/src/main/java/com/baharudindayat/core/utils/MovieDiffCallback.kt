package com.baharudindayat.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.baharudindayat.core.domain.model.Movie

class MovieDiffCallback (private val mOldMovie: List<Movie>,private  val mNewMovie: List<Movie>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldMovie.size
    }

    override fun getNewListSize(): Int {
        return mNewMovie.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldMovie[oldItemPosition].id == mNewMovie[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = mOldMovie[oldItemPosition]
        val newMovie = mNewMovie[newItemPosition]
        return oldMovie.id == newMovie.id && oldMovie.originalTitle == newMovie.originalTitle && oldMovie.posterPath == newMovie.posterPath
    }

}