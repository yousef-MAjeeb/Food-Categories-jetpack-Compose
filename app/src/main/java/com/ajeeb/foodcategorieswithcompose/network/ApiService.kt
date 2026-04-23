package com.ajeeb.foodcategorieswithcompose.network

import com.ajeeb.foodcategorieswithcompose.model.Category
import com.ajeeb.foodcategorieswithcompose.model.Root
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): Root

}