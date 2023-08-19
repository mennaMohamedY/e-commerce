package com.example.myapplication

import com.example.myapplication.fragments.ChosenCategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/v1/categories")
    fun getAllCAtegories():Call<AllCategoriesResponse>


    @GET("api/v1/categories/{id}")
    fun getSpecificCAtegory(@Path("id") id: String?):Call<ChosenCategoryResponse>

    @GET("api/v1/categories/{subCategoryId}/subcategories")
    fun getSubCategoriesFromCategory(@Path("subCategoryId") subCategoryId:String):Call<SubCategoriesResponse>

    @GET("api/v1/products/")
    fun getAllProducts():Call<AllProductsResponse>


}