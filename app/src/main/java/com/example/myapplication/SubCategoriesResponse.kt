package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class SubCategoriesResponse(

	@field:SerializedName("metadata")
	val metadata: Metadata3? = null,

	@field:SerializedName("data")
	val data: List<DataItem3?>? = null,

	@field:SerializedName("results")
	val results: Int? = null
)

data class DataItem3(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Metadata3(

	@field:SerializedName("numberOfPages")
	val numberOfPages: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("currentPage")
	val currentPage: Int? = null
)
