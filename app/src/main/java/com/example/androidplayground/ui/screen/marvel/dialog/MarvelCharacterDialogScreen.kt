package com.example.androidplayground.ui.screen.marvel.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidplayground.R
import com.example.androidplayground.ui.component.CardContentText
import com.example.androidplayground.ui.component.CardDescriptionText
import com.example.androidplayground.ui.component.RemoteImage
import com.example.androidplayground.ui.screen.marvel.dialog.MarvelCharacterDialogScreenId.CHARACTER_LAYOUT_ID
import com.example.androidplayground.ui.screen.marvel.dialog.MarvelCharacterDialogScreenId.DESCRIPTION_LAYOUT_ID
import com.example.androidplayground.ui.screen.marvel.dialog.MarvelCharacterDialogScreenId.IMAGE_LAYOUT_ID
import com.example.androidplayground.ui.screen.marvel.dialog.MarvelCharacterDialogScreenId.NAME_LAYOUT_ID
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme
import com.example.androidplayground.ui.theme.Dimens
import com.example.androidplayground.ui.theme.GradientEnd
import com.example.androidplayground.ui.theme.GradientStart
import com.example.androidplayground.ui.util.preview.previewCharacterDefault
import com.example.domain.entity.MarvelCharacter

@Composable
fun MarvelCharacterDialogScreen(
    marvelCharacter: MarvelCharacter,
    viewModel: MarvelCharacterDialogViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val data: MarvelCharacterDialogViewModel.MarvelCharacterDialogData =
        viewModel.state.collectAsState().value

    // 🔐 Side-effect control by LaunchedEffect based on state
    LaunchedEffect(data.state) {
        if (data.state == MarvelCharacterDialogViewModel.MarvelCharacterDialogState.ON_DIALOG_DISMISSED) {
            onDismiss()
        }
    }

    // 🖼️ if need it Render the UI
    if (data.state == MarvelCharacterDialogViewModel.MarvelCharacterDialogState.DRAW) {
        DrawScreen(
            viewModel = viewModel,
            marvelCharacter = marvelCharacter
        )
    }
}

@Composable
fun DrawScreen(viewModel: MarvelCharacterDialogViewModel, marvelCharacter: MarvelCharacter) {
    var showDialog by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    val verticalGradient = Brush.verticalGradient(colors = listOf(GradientStart, GradientEnd))

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                viewModel.onDismiss()
                showDialog = false
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = Dimens.MarvelCharacterDialogScreenCardPadding),
                shape = RoundedCornerShape(topEnd = Dimens.RoundedCornerShape, bottomStart = Dimens.RoundedCornerShape),
                border = BorderStroke(Dimens.BorderStroke, Color.Black)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = verticalGradient)
                        .scrollable(
                            state = scrollState,
                            orientation = Orientation.Vertical
                        ),
                    constraintSet = getScreenConstraintSet()
                ) {
                    CardContentText(
                        text = stringResource(
                            id = R.string.marvel_character_detail_screen_id,
                            marvelCharacter.id
                        ),
                        modifier = Modifier
                            .layoutId(CHARACTER_LAYOUT_ID)
                            .padding(Dimens.MarvelCharacterDialogScreenCardCharacterPadding)
                    )
                    RemoteImage(
                        imgPath = marvelCharacter.img,
                        contentDescription = stringResource(id = R.string.marvel_character_detail_screen_content_description),
                        modifier = Modifier.layoutId(IMAGE_LAYOUT_ID)
                    )
                    CardContentText(
                        text = marvelCharacter.name,
                        modifier = Modifier
                            .layoutId(NAME_LAYOUT_ID)
                            .fillMaxWidth()
                            .padding(top = Dimens.MarvelPadding)
                    )
                    CardDescriptionText(
                        text = marvelCharacter.description.ifEmpty {
                            stringResource(id = R.string.marvel_character_detail_screen_no_description)
                        },
                        modifier = Modifier
                            .layoutId(DESCRIPTION_LAYOUT_ID)
                            .fillMaxWidth()
                            .padding(top = Dimens.MarvelPadding)
                    )
                }
            }
        }
    }
}

private fun getScreenConstraintSet() = ConstraintSet {
    val id = createRefFor(CHARACTER_LAYOUT_ID)
    val image = createRefFor(IMAGE_LAYOUT_ID)
    val name = createRefFor(NAME_LAYOUT_ID)
    val description = createRefFor(DESCRIPTION_LAYOUT_ID)

    createVerticalChain(image, name, description)

    constrain(id) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
    }
    constrain(image) {
        start.linkTo(parent.start)
        top.linkTo(id.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(name.top)
    }
    constrain(name) {
        start.linkTo(parent.start)
        top.linkTo(image.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(description.top)
    }
    constrain(description) {
        start.linkTo(parent.start)
        top.linkTo(name.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@Preview(showBackground = true)
@Composable
private fun MarvelCharacterDialogScreenPreview() {
    AndroidPlayGroundTheme {
        MarvelCharacterDialogScreen(previewCharacterDefault) {}
    }
}

private object MarvelCharacterDialogScreenId {
    const val CHARACTER_LAYOUT_ID = "CHARACTER_LAYOUT_ID"
    const val IMAGE_LAYOUT_ID = "IMAGE_LAYOUT_ID"
    const val NAME_LAYOUT_ID = "NAME_LAYOUT_ID"
    const val DESCRIPTION_LAYOUT_ID = "DESCRIPTION_LAYOUT_ID"
}
