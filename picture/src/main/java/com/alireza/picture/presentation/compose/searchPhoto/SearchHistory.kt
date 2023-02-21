package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.picture.R
import com.alireza.picture.domain.model.searchHistory.SearchHistory
import com.alireza.ui.theme.FlickrFinderTheme

@Composable
fun SearchHistoryList(
    searchList: SearchPhotoHistoryState,
    modifier: Modifier = Modifier,
    onItemClick: (searchItem: SearchHistory) -> Unit,
    onItemDeleteClick: (searchHistory: SearchHistory) -> Unit,
    onClearAllClick: () -> Unit,
) {
    if (searchList is SearchHistoryList)
    Column {
        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
            itemsIndexed(searchList.lastHistory) { index, item ->
                SearchHistoryCard(
                    searchHistory = item,
                    modifier = modifier,
                    onItemClick = onItemClick,
                    onItemDeleteClick = onItemDeleteClick
                )
                if (index < searchList.lastHistory.lastIndex)
                    Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }

        if (searchList.lastHistory.isNotEmpty())
        TextButton(
            onClick = onClearAllClick, modifier = modifier
                .align(Alignment.End)
        ) {
            Text(
                text = stringResource(id = R.string.fragmentSearchPhoto_clear_history),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHistoryCard(
    searchHistory: SearchHistory,
    modifier: Modifier = Modifier,
    onItemClick: (searchItem: SearchHistory) -> Unit,
    onItemDeleteClick: (searchHistory: SearchHistory) -> Unit
) {

    Surface(
        modifier = modifier,
        onClick = { onItemClick(searchHistory) }) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = searchHistory.query,
                modifier = modifier.weight(1f),
                style = MaterialTheme.typography.labelMedium,
            )

            IconButton(onClick = { onItemDeleteClick(searchHistory) }) {
                Icon(
                    painter = painterResource(id = com.alireza.core.R.drawable.ic_delete),
                    tint= Color.LightGray,
                    contentDescription = "search_history_delete",
                    modifier = modifier.size(16.dp)
                )
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSearchHistoryCard() {
    FlickrFinderTheme() {
        SearchHistoryCard(
            searchHistory = SearchHistory("Alireza"),
            onItemClick = {},
            onItemDeleteClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchHistoryList() {
    FlickrFinderTheme() {
        SearchHistoryList(
            searchList = SearchHistoryList(
                mutableListOf(
                    SearchHistory("Alireza"),
                    SearchHistory("Alireza")
                )
            ),
            onItemClick = {},
            onItemDeleteClick = {},
            onClearAllClick = {}
        )
    }
}