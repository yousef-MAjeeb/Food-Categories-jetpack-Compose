package com.ajeeb.foodcategorieswithcompose.util

import com.ajeeb.foodcategorieswithcompose.model.Root

sealed class UiState{
    object Loading : UiState()
    data class Success(val data: Root) : UiState()
    data class Error(val message: String) : UiState()
}