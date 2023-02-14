package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.ui.ScreenContainer

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotoViewModel = hiltViewModel(),
    onPhotoDetailNavigation: (photoId: String, photoUrl: String) -> Unit
) {
    val historyState = viewModel.searchHistoryState.collectAsStateWithLifecycle()
    val errorState = viewModel.errorState.collectAsStateWithLifecycle()

}


@Composable
fun SearchPhotoScreen(historyState: SearchHistoryList, errorState: ErrorState) {
    ScreenContainer(errorState = errorState, onRetryClick = { /*TODO*/ }) {
        SearchList(searchList = historyState, onItemClick = {}, onItemDeleteClick = {}) {

        }
    }
}


