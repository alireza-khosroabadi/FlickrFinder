package com.alireza.ui.meptyState

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.core.data.error.AppError
import com.alireza.ui.theme.FlickrFinderTheme


@Composable
fun EmptyState(uiState: AppError, modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    when (uiState) {
        is AppError.NetworkConnection -> {}
        is AppError.SocketTimeOut -> RetryActionError(
            appError = uiState,
            onRetryClick = onRetryClick
        )
        is AppError.UnknownError -> {}
        is AppError.UnknownHostException -> {}
        is AppError.EmptyState -> RetryActionError(appError = uiState, onRetryClick = onRetryClick)
    }
}


@Composable
fun RetryActionError(appError: AppError, modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(appError.icon),
                contentDescription = stringResource(id = appError.title),
                modifier = modifier.size(150.dp)
            )

            Text(
                text = stringResource(id = appError.title),
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(16.dp)
            )


            Button(onClick = onRetryClick, modifier = modifier.padding(top = 32.dp)) {
                Text(text = stringResource(id = com.alireza.core.R.string.empty_state_view_btn))
            }
        }


    }
}

@Preview
@Composable
fun PreviewEmptyState() {
    FlickrFinderTheme() {
        EmptyState(
            uiState = AppError.SocketTimeOut(), onRetryClick = {}
        )
    }
}