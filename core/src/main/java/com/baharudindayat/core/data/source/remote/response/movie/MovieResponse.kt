package com.baharudindayat.core.data.source.remote.response.movie

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
) : Parcelable