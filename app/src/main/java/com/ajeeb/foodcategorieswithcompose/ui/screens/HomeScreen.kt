package com.ajeeb.foodcategorieswithcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajeeb.foodcategorieswithcompose.ui.components.CategoryInfoCard
import com.ajeeb.foodcategorieswithcompose.ui.components.MyTopAppBar
import com.ajeeb.foodcategorieswithcompose.util.UiState
import com.ajeeb.foodcategorieswithcompose.viewmodel.CategoryViewModel

@Composable
fun HomeScreen(categoryViewModel: CategoryViewModel = viewModel()){

    val response by categoryViewModel.foodCategory.collectAsState()

    Scaffold(
        topBar = { MyTopAppBar() },
        content = { paddingValues ->
            when (response) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Snackbar(
                            action = {
                                TextButton(onClick = {
                                    categoryViewModel.retryFetch()
                                }) {
                                    Text("Retry")
                                }
                            }
                        ) {
                            Text("Failed to load data.")
                        }
                    }
                }
                is UiState.Success -> {
                    val categories = (response as UiState.Success).data
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        items(categories) { item ->
                            CategoryInfoCard(
                                item.strCategoryThumb,
                                item.strCategory,
                                item.strCategoryDescription
                            )
                        }
                    }
                }
            }
        }
    )
}