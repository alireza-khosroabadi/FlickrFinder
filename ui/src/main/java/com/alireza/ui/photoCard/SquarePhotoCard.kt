package com.alireza.ui.photoCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alireza.ui.theme.FlickrFinderTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@Composable
fun SquarePhotoCard(
    photo: PhotoModel,
    onPhotoClick: (photoUrl: String, photoId: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        AsyncImage(
            model = photo.photoUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            modifier = modifier
                .clickable(onClick = {
                    onPhotoClick(
                        photo.photoUrl,
                        photo.photoId
                    )
                })
                .padding(2.dp)
                .size(100.dp),
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSquarePhotoCard() {

    FlickrFinderTheme {
        SquarePhotoCard(
            PhotoModel(
                "",
                "https://bscholarly.com/wp-content/uploads/2022/08/Strongest-car-body-in-the-world-768x497.jpg"
            ), { photoUrl, photoId -> })
    }

}


