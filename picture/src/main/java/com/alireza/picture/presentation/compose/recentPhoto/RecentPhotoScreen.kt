package com.alireza.picture.presentation.compose.recentPhoto

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alireza.core.presentation.viewModel.BaseViewModelState
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.picture.R
import com.alireza.ui.ScreenContainer
import com.alireza.ui.progressBar.FlickrFinderCircularProgressBar


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecentPhotoScreen(
    viewModel: RecentPhotoViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit,
    onNavigateFavorite: () -> Unit
) {
    val uiState: RecentPhotoState by viewModel.recentPhotoState.collectAsStateWithLifecycle()
    val errorState by viewModel.errorState.collectAsStateWithLifecycle()
    RecentPhotoScreen(
        uiState = uiState,
        errorState = errorState,
        onNavigateToSearch = onNavigateToSearch,
        onNavigateFavorite = {}) {
        viewModel.loadRecentPhoto(true)
    }
}

@Composable
fun RecentPhotoScreen(
    uiState: RecentPhotoState,
    errorState: BaseViewModelState,
    modifier: Modifier = Modifier,
    onNavigateToSearch: () -> Unit,
    onNavigateFavorite: () -> Unit,
    onErrorStateAction: () -> Unit
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        TopLayout(modifier, onNavigateToSearch, onNavigateFavorite)
        ScreenContainer(modifier = Modifier, errorState = errorState, onRetryClick = {
            onErrorStateAction()
        }) {
            FullScreen(uiState = uiState, modifier)
        }
    }

}

@Composable
private fun TopLayout(
    modifier: Modifier,
    onNavigateToSearch: () -> Unit,
    onNavigateFavorite: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.fragmentRecentPhoto_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier.weight(1f)
        )

        IconButton(
            onClick = onNavigateFavorite,
            modifier = modifier.testTag("btn_search_photo_nav")
        ) {
            Icon(
                painter = painterResource(id = com.alireza.core.R.drawable.ic_no_favorite),
                contentDescription = "search_photo"
            )
        }
        Spacer(modifier = modifier.size(8.dp))
        IconButton(onClick = onNavigateToSearch) {
            Icon(
                painter = painterResource(id = com.alireza.core.R.drawable.ic_search),
                contentDescription = null
            )
        }
    }
}


@Composable
fun FullScreen(uiState: RecentPhotoState, modifier: Modifier = Modifier) {
    when (uiState) {
        Loading -> {
            FlickrFinderCircularProgressBar(modifier = modifier.testTag("loading_progress"))
        }
        is RecentPhotoList -> {
            RecentPhotoList(
                uiState.photoList,
                modifier.testTag("recentPhotoList")
            ) { photoId, photoUrl ->

            }
        }
    }
}