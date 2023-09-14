package com.baharudindayat.nontonin.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudindayat.core.domain.model.Movie
import com.baharudindayat.core.utils.MovieDiffCallback
import com.baharudindayat.nontonin.databinding.ItemListBinding
import com.bumptech.glide.Glide

class ListFilmAdapter : RecyclerView.Adapter<ListFilmAdapter.ViewHolder>(){

    private val items = ArrayList<Movie>()
    var listener: ((Movie) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFilmAdapter.ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFilmAdapter.ViewHolder, position: Int) {
        val data = items[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = items.size

    fun setData(newListData: List<Movie>?) {
        val diffCallback = newListData?.let { MovieDiffCallback(this.items, it) }
        val diffResult = diffCallback?.let { DiffUtil.calculateDiff(it) }
        this.items.clear()
        newListData?.let { this.items.addAll(it) }
        diffResult?.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                tvFilmName.text = item.originalTitle
                tvReleaseDate.text = item.releaseDate
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                    .into(ivFilmLogo)

            }
        }
        init{
            binding.root.setOnClickListener {
                listener?.invoke(items[adapterPosition])
            }
        }
    }


}