package com.alireza.picture.presentation.compose.searchPhoto.previewParameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.alireza.picture.domain.model.searchPhoto.SearchPhoto

class SearchPhotoListParameter : PreviewParameterProvider<SearchPhoto> {
    override val values: Sequence<SearchPhoto>
        get() = sequenceOf(
            SearchPhoto("1", "Alireza 1", "Alireza 2"),
            SearchPhoto("1", "Alireza 1")
        )
}
