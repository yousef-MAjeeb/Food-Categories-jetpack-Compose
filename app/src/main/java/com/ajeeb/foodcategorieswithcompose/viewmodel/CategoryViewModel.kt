package com.ajeeb.foodcategorieswithcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajeeb.foodcategorieswithcompose.model.Category
import com.ajeeb.foodcategorieswithcompose.model.Root
import com.ajeeb.foodcategorieswithcompose.network.RetrofitClint.apiService
import com.ajeeb.foodcategorieswithcompose.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private val _foodCategory = MutableStateFlow<UiState<Root>>(UiState.Loading)
    val foodCategory = _foodCategory.asStateFlow()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch{
            try {
                val result = apiService.getCategories()
                _foodCategory.value = UiState.Success(result)
            } catch (e: Exception){
                _foodCategory.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun retryFetch() {
        fetchCategories()
    }
}