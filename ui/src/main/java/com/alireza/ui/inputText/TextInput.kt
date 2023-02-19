package com.alireza.ui.inputText

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alireza.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    hint: String = "",
    modifier: Modifier = Modifier,
    @DrawableRes endIcon: Int? = null,
    onEndIconClick: (() -> Unit)? = null,
    onTextChanged: ((text: String) -> Unit)? = null,
) {
    var searchQuery by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequest = remember { FocusRequester() }

    BasicTextField(
        value = searchQuery,
        onValueChange = { newText ->
            searchQuery = newText
            onTextChanged?.invoke(newText)
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 64.dp) // margin left and right
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
               Row(Modifier.weight(1f)) {
                   if (searchQuery.isEmpty()) {
                       Text(
                           text = hint,
                           fontSize = 18.sp,
                           fontWeight = FontWeight.Normal,
                           color = Color.LightGray
                       )
                   }

                   innerTextField()
               }
                if (endIcon!=null) {
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Icon(
                        painter = painterResource(id = endIcon),
                        contentDescription = "Favorite icon",
                        tint = Color.DarkGray
                    )
                }
            }
        }
    )

 /*   Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
            .border(border = BorderStroke(1.dp, Color.LightGray), shape = Shapes.Full),
        shape = CircleShape
    ) {
        Row(modifier = modifier.fillMaxWidth().background(Color.White), verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                                onTextChanged?.invoke(it)},
                interactionSource = interactionSource,
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
                    .background(Color.White)
                    .focusRequester(focusRequest),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions(onSearch = { onEndIconClick?.invoke() })
            )
            if (endIcon != null)
                IconButton(onClick = { onEndIconClick?.invoke() }) {
                    Icon(
                        painter = painterResource(id = endIcon),
                        contentDescription = null
                    )
                }
        }
    }
*/
}

@Preview(showBackground = true)
@Composable
fun PreviewTextInput() {
    TextInput(endIcon = R.drawable.ic_search)
}