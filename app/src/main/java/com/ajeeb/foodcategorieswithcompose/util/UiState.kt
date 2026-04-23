package com.ajeeb.foodcategorieswithcompose.util

import com.ajeeb.foodcategorieswithcompose.model.Category
import com.ajeeb.foodcategorieswithcompose.model.Root


sealed class UiState{
    object Loading : UiState()
    data class Success(val data: List<Category>) : UiState()
    data class Error(val message: String) : UiState()
}