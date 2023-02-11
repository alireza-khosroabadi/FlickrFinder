package com.alireza.picture.presentation.compose.recentPhoto

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alireza.picture.domain.model.recentPhoto.RecentPhoto
import com.alireza.ui.photoCard.PhotoModel
import com.alireza.ui.photoCard.SquarePhotoCard
import com.alireza.ui.theme.FlickrFinderTheme




@Composable
fun RecentPhotoList(
    photoList: List<RecentPhoto>,
    modifier: Modifier = Modifier,
    onPhotoClick: (photoId: String, photoUrl: String) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier) {
        items(photoList) { item ->
            SquarePhotoCard(photo = PhotoModel(item.id, item.url), onPhotoClick = onPhotoClick)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewRecentPhotoList(){
    FlickrFinderTheme {
        RecentPhotoList(photoList = mutableListOf(), onPhotoClick = {photoId, photoUrl ->  })
    }
}