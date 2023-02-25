package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto
import com.alireza.picture.presentation.compose.searchPhoto.previewParameter.SearchPhotoListParameter


@Composable
fun SearchPhotoList(
    photoList: SearchPhotoList,
    modifier: Modifier= Modifier,
    onPhotoClick: (photoId: String, photoUrl: String) -> Unit
) {

    LazyColumn() {
        items(photoList.photoList, key = { item -> item.id }) { item ->
            PhotoCard(photo = item, onPhotoClick = onPhotoClick)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoCard(
    photo: SearchPhoto,
    modifier: Modifier = Modifier,
    onPhotoClick: (photoId: String, photoUrl: String) -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(16.dp))
            .padding(vertical = 8.dp)
    ) {
        Box {
            AsyncImage(
                model = photo.url,
                contentDescription = "searched_photo",
                contentScale= ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
                    .clipToBounds()
            )

            if (photo.title.isNotEmpty())
                Box(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Black,
                                    Color.Gray
                                ), tileMode = TileMode.Decal
                            ),
                            alpha = 0.7f
                        )
                ) {
                    Text(
                        text = photo.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        modifier = modifier
                            .align(Alignment.CenterStart)
                            .padding(horizontal = 16.dp)
                    )
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPhotoCard(@PreviewParameter(SearchPhotoListParameter::class) searchPhoto: SearchPhoto) {
    PhotoCard(photo = searchPhoto) { photoId, photoUrl -> }
}



