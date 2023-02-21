package com.alireza.picture.presentation.compose.searchPhoto

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alireza.picture.R
import com.alireza.ui.inputText.TextInput


@Composable
fun SearchBox(
    modifier: Modifier = Modifier, onBackClickListener: () -> Unit,
    onEndIconClick: ((text: String) -> Unit)
) {

    var queryString by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextInput(
            value = queryString,
            onValueChange = { queryString = it },
            hint = stringResource(id = R.string.fragmentSearchPhoto_search),
            modifier = modifier.weight(1f),
            endIcon = com.alireza.ui.R.drawable.ic_search,
            onEndIconClick = onEndIconClick,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onEndIconClick?.invoke(queryString) })
        )

        IconButton(
            onClick = onBackClickListener,
            modifier = modifier
                .testTag("btn_back")
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
    SearchBox(onBackClickListener = {}) { text -> }
}