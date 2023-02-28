package com.saad.homeflix.data.models

import kotlinx.parcelize.Parcelize
import com.fasterxml.jackson.annotation.JsonProperty
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
data class ResponseMovies(

	@field:JsonProperty("page")
	val page: Int? = null,

	@field:JsonProperty("total_pages")
	val totalPages: Int? = null,

	@field:JsonProperty("results")
	val results: List<ResultsItem>? = null,

	@field:JsonProperty("total_results")
	val totalResults: Int? = null
) : Parcelable

@Parcelize
@Entity(tableName = "movie")
data class ResultsItem(

	//added this val for room db
	val isLiked: Boolean = false,

	@field:JsonProperty("overview")
	val overview: String? = null,

	@field:JsonProperty("original_language")
	val originalLanguage: String? = null,

	@field:JsonProperty("original_title")
	val originalTitle: String? = null,

	@field:JsonProperty("video")
	val video: Boolean? = null,

	@field:JsonProperty("title")
	val title: String? = null,

	@field:JsonProperty("genre_ids")
	val genreIds: List<Int?>? = null,

	@field:JsonProperty("poster_path")
	val posterPath: String? = null,

	@field:JsonProperty("backdrop_path")
	val backdropPath: String? = null,

	@field:JsonProperty("release_date")
	val releaseDate: String? = null,

	@field:JsonProperty("popularity")
	val popularity: Double? = null,

	@field:JsonProperty("vote_average")
	val voteAverage: Double? = null,

	@PrimaryKey
	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("adult")
	val adult: Boolean? = null,

	@field:JsonProperty("vote_count")
	val voteCount: Int? = null

) : Parcelable
