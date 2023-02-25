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
import com.alireza.ui.progressBar.FlickrFinderCircularProgressBar

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotoViewModel = hiltViewModel(),
    onPhotoDetailNavigation: (photoId: String, photoUrl: String) -> Unit,
    onBackClickListener: () -> Unit,
    modifier: Modifier = Modifier
) {
    val historyState = viewModel.searchHistoryState.collectAsStateWithLifecycle()
    val errorState = viewModel.errorState.collectAsStateWithLifecycle()
    val photoState = viewModel.searchPhotoState.collectAsStateWithLifecycle()

    var queryString by remember { mutableStateOf("") }

    SearchPhotoScreen(
        queryString,
        photoState = photoState.value,
        historyState = historyState.value,
        errorState = errorState.value,
        onBackClickListener = onBackClickListener,
        onEndIconClick = {viewModel.searchPhoto(queryString) },
        onItemClick = { item -> viewModel.searchPhoto(item.query) },
        onItemDeleteClick = { item -> viewModel.removeHistory(item) },
        onClearAllClick = { viewModel.clearAllHistory() },
        onTextChanged = {viewModel.searchPhoto(queryString) },
        onPhotoClick = onPhotoDetailNavigation,
        modifier = modifier
    )

}


@Composable
fun SearchPhotoScreen(
    searchQuery:String,
    photoState: SearchPhotoState,
    historyState: SearchPhotoHistoryState,
    errorState: BaseViewModelState,
    onBackClickListener: () -> Unit,
    onEndIconClick: (() -> Unit),
    onItemClick: (searchItem: SearchHistory) -> Unit,
    onItemDeleteClick: (searchHistory: SearchHistory) -> Unit,
    onClearAllClick: () -> Unit,
    onTextChanged:(text:String)->Unit,
    onPhotoClick: (photoId: String, photoUrl: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(top = 16.dp, start = 16.dp)) {
        SearchBox(
            onBackClickListener = onBackClickListener,
            onEndIconClick = onEndIconClick,
            modifier = modifier,
            value = searchQuery,
            onTextChanged = onTextChanged
        )
        ScreenContainer(
            errorState = errorState,
            onRetryClick = { if (searchQuery.isNotEmpty()) onEndIconClick },
            modifier = modifier.padding(end = 16.dp)) {
            Box {
                if (historyState is SearchHistoryList
                    && historyState.lastHistory.isNotEmpty()
                    && (photoState is SearchPhotoList).not()
                )
                    SearchHistoryList(
                        searchList = historyState,
                        onItemClick = onItemClick,
                        onItemDeleteClick = onItemDeleteClick,
                        onClearAllClick = onClearAllClick,
                        modifier = modifier
                    )
                if (photoState is SearchPhotoLoading)
                    FlickrFinderCircularProgressBar(modifier = modifier.testTag("loading_progress"))
                else if (photoState is SearchPhotoList)
                    SearchPhotoList(
                        photoList = (photoState as SearchPhotoList),
                        onPhotoClick = onPhotoClick
                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchPhotoScreen() {
//    SearchPhotoScreen(SearchHistoryList(mutableListOf()), Initialize, {}, {}, {}, {}, {})
}



