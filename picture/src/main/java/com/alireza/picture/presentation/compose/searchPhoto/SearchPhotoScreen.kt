package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alireza.core.presentation.viewModel.BaseViewModelState
import com.alireza.ui.ScreenContainer
import com.alireza.ui.inputText.TextInput

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SearchPhotoScreen(
    viewModel: SearchPhotoViewModel = hiltViewModel(),
    onPhotoDetailNavigation: (photoId: String, photoUrl: String) -> Unit,
    onBackClickListener: () -> Unit
) {
    val historyState = viewModel.searchHistoryState.collectAsStateWithLifecycle()
    val errorState = viewModel.errorState.collectAsStateWithLifecycle()

    SearchPhotoScreen(
        historyState = historyState.value,
        errorState = errorState.value,
        onBackClickListener = onBackClickListener
    )

}


@Composable
fun SearchPhotoScreen(
    historyState: SearchPhotoHistoryState,
    errorState: BaseViewModelState,
    onBackClickListener: () -> Unit
) {
    Column {
        TopLayout(onBackClickListener = onBackClickListener)
        ScreenContainer(errorState = errorState, onRetryClick = { /*TODO*/ }) {
            SearchHistoryList(searchList = historyState, onItemClick = {}, onItemDeleteClick = {}) {

            }
        }
    }
}

@Composable
fun TopLayout(modifier: Modifier = Modifier, onBackClickListener: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        TextInput(
            modifier = modifier.fillMaxWidth(),
            endIcon = com.alireza.ui.R.drawable.ic_search
        )

        IconButton(
            onClick = onBackClickListener,
            modifier = modifier
                .testTag("btn_back")
                .weight(1f)
        ) {
            Icon(
                painter = painterResource(id = com.alireza.ui.R.drawable.ic_arrow_right),
                contentDescription = "back"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopLayout() {
    TopLayout() {}
}


