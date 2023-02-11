package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchPhotoScreen(viewModel:SearchPhotoViewModel = hiltViewModel()){
    
    Text(text = "SEARCH")
}