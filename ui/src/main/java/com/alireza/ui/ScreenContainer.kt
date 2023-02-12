package com.alireza.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alireza.core.presentation.viewModel.BaseViewModelState
import com.alireza.core.presentation.viewModel.ErrorState
import com.alireza.core.presentation.viewModel.ExceptionState
import com.alireza.core.presentation.viewModel.Initialize
import com.alireza.ui.meptyState.EmptyState

@Composable
fun ScreenContainer(
    errorState: BaseViewModelState,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (errorState != Initialize) {
            when (errorState) {
                is ErrorState -> {}// EmptyState(uiState = errorState, modifier = modifier) {}
                is ExceptionState -> EmptyState(uiState = errorState.error, modifier = modifier) {
                    onRetryClick()
                }
            }
        } else {
            content()
        }
    }
}