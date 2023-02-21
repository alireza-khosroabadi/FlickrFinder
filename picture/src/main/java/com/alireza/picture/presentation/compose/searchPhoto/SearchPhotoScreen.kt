package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alireza.core.presentation.viewModel.BaseViewModelState
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.Initialize
import com.alireza.picture.R
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.ui.ScreenContainer
import com.alireza.ui.inputText.TextInput

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotoViewModel = hiltViewModel(),
    onPhotoDetailNavigation: (photoId: String, photoUrl: String) -> Unit,
    onBackClickListener: () -> Unit,
    modifier: Modifier=Modifier
) {
    val historyState = viewModel.searchHistoryState.collectAsStateWithLifecycle()
    val errorState = viewModel.errorState.collectAsStateWithLifecycle()

    SearchPhotoScreen(
        historyState = historyState.value,
        errorState = errorState.value,
        onBackClickListener = onBackClickListener,
        onEndIconClick = { text -> viewModel.searchPhoto(text) },
        onItemClick = {item -> viewModel.searchPhoto(item.query)},
        onItemDeleteClick = {item -> viewModel.removeHistory(item) },
        onClearAllClick = { viewModel.clearAllHistory() },
        modifier = modifier
    )

}


@Composable
fun SearchPhotoScreen(
    historyState: SearchPhotoHistoryState,
    errorState: BaseViewModelState,
    onBackClickListener: () -> Unit,
    onEndIconClick: ((text: String) -> Unit),
    onItemClick: (searchItem: SearchHistory) -> Unit,
    onItemDeleteClick: (searchHistory: SearchHistory) -> Unit,
    onClearAllClick: () -> Unit,
    modifier:Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 16.dp, start = 16.dp)) {
        SearchBox(
            onBackClickListener = onBackClickListener,
            onEndIconClick = onEndIconClick,
            modifier = modifier
        )
        ScreenContainer(errorState = errorState, onRetryClick = { /*TODO*/ }) {
            SearchHistoryList(
                searchList = historyState,
                onItemClick = onItemClick,
                onItemDeleteClick = onItemDeleteClick,
                onClearAllClick = onClearAllClick
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchPhotoScreen() {
    SearchPhotoScreen(SearchHistoryList(mutableListOf()), Initialize, {}, {}, {}, {}, {})
}



