package com.alireza.ui.inputText

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alireza.core.R
import com.alireza.ui.theme.FlickrFinderTheme


class TextInputUserInputState(initializeValue:String, hint: String,caption: String?){
    var text by mutableStateOf(initializeValue)
        private set

    var caption by mutableStateOf(caption)
        private set

    var hint by mutableStateOf(hint)
        private set

    fun updateText(newText:String){
        text = newText
    }

    val isHint: Boolean
        get() = text == hint

    companion object {
        val saver: Saver<TextInputUserInputState, *> = listSaver(
            save = { listOf(it.hint, it.caption, it.text) },
            restore = {
                TextInputUserInputState( initializeValue = it[2].toString(), hint= it[1].toString(),caption= it[2])
            }
        )
    }
}

@Composable
fun rememberTextInputUserState(initializeValue: String="", hint: String, caption: String?=null): TextInputUserInputState =
    rememberSaveable(initializeValue, saver = TextInputUserInputState.saver){
        TextInputUserInputState(initializeValue,hint , caption)
    }

@Composable
fun TextInput(
    state: TextInputUserInputState = rememberTextInputUserState(hint = ""),
    modifier: Modifier = Modifier,
    @DrawableRes endIcon: Int? = null,
    onEndIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequest = remember { FocusRequester() }

    BasicTextField(
        value = state.text,
        onValueChange = {state.updateText(it)},
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            DecorationBox(modifier, state.text, state.hint,state.caption, innerTextField, endIcon, onEndIconClick)
        }
    )
}

@Composable
private fun DecorationBox(
    modifier: Modifier,
    searchQuery: String,
    hint: String?,
    caption:String?,
    innerTextField: @Composable () -> Unit,
    endIcon: Int?,
    onEndIconClick: (() -> Unit)?,
    tint: Color = LocalContentColor.current
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (caption != null) {
            Spacer(Modifier.width(8.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = caption,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = tint
            )
        }
        Box(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (searchQuery.isEmpty()) {
                Text(
                    text = hint.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray
                )
                Spacer(Modifier.width(8.dp))
            }
            innerTextField()
        }
        if (endIcon != null) {
            Spacer(modifier = Modifier.width(width = 8.dp))
            IconButton(onClick = { onEndIconClick?.invoke() }) {
                Icon(
                    painter = painterResource(id = endIcon),
                    contentDescription = "Favorite icon",
                    tint = Color.DarkGray,
                    modifier = modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun CraneUserInput(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    tint: Color = LocalContentColor.current
) {
    CraneBaseUserInput(
        modifier = modifier,
        onClick = onClick,
        caption = caption,
        vectorImageId = vectorImageId,
        tintIcon = { text.isNotEmpty() },
        tint = tint
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium.copy(color = tint))
    }
}


//@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CraneBaseUserInput(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    showCaption: () -> Boolean = { true },
    tintIcon: () -> Boolean,
    tint: Color = LocalContentColor.current,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = MaterialTheme.colorScheme.background
    ) {
        Row(Modifier.padding(all = 12.dp)) {
            if (vectorImageId != null) {
                Icon(
                    modifier = Modifier.size(24.dp, 24.dp),
                    painter = painterResource(id = vectorImageId),
                    tint = if (tintIcon()) tint else Color(0x80FFFFFF),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
            }
            if (caption != null && showCaption()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = caption,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = tint
                )
                Spacer(Modifier.width(8.dp))
            }
            Row(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextInput() {
    val state = rememberTextInputUserState(hint = "Hint", caption = "Caption")
    TextInput(state = state , endIcon = R.drawable.ic_search)
}

@Preview(showBackground = true)
@Composable
fun PreviewInput() {
    FlickrFinderTheme {
        Surface {
            CraneBaseUserInput(
                tintIcon = { true },
                vectorImageId = R.drawable.ic_search,
                caption = "Caption",
                showCaption = { true }
            ) {
                Text(text = "text", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}