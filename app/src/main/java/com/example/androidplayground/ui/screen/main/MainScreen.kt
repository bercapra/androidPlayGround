package com.example.androidplayground.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.androidplayground.R
import com.example.androidplayground.ui.component.BackgroundImage
import com.example.androidplayground.ui.component.ContentText
import com.example.androidplayground.ui.component.TitleText
import com.example.androidplayground.ui.screen.main.MainScreenId.BUTTON_LAYOUT_ID
import com.example.androidplayground.ui.screen.main.MainScreenId.CONTENT_LAYOUT_ID
import com.example.androidplayground.ui.screen.main.MainScreenId.TITLE_LAYOUT_ID
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme
import com.example.androidplayground.ui.theme.Dimens
import com.example.androidplayground.ui.util.OnLifecycleEventEffect

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onButtonPressed: () -> Unit
) {
    val data: MainViewModel.MainData = viewModel.state.collectAsState().value

    OnLifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.draw()
    }

    OnLifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        viewModel.onStop()
    }

    when (data.state) {
        MainViewModel.MainState.IDLE -> {}
        MainViewModel.MainState.DRAW -> DrawScreen(viewModel)
        MainViewModel.MainState.GO_TO_CHARACTER_LIST -> {
            LaunchedEffect(Unit) {
                onButtonPressed()
            }
        }
    }
}

@Composable
private fun DrawScreen(viewModel: MainViewModel) {
    BackgroundImage()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MarvelPadding),
        constraintSet = getScreenConstrainSet()
    ) {
        TitleText(
            text = stringResource(R.string.main_screen_title),
            modifier = Modifier
                .layoutId(TITLE_LAYOUT_ID)
                .fillMaxWidth()

        )
        ContentText(
            text = stringResource(R.string.main_screen_content),
            modifier = Modifier
                .layoutId(CONTENT_LAYOUT_ID)
                .fillMaxWidth()
        )
        Button(
            onClick = { viewModel.onButtonPressed() },
            modifier = Modifier.layoutId(BUTTON_LAYOUT_ID),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Red
            )
        ) {
            Text(
                text = stringResource(R.string.main_screen_button),
            )
        }
    }
}

private fun getScreenConstrainSet() = ConstraintSet {
    val title = createRefFor(TITLE_LAYOUT_ID)
    val content = createRefFor(CONTENT_LAYOUT_ID)
    val button = createRefFor(BUTTON_LAYOUT_ID)

    constrain(title) {
        top.linkTo(parent.top)
        bottom.linkTo(content.top)
    }

    constrain(content) {
        top.linkTo(title.bottom)
        bottom.linkTo(button.top)
    }

    constrain(button) {
        top.linkTo(content.bottom)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AndroidPlayGroundTheme {
        MainScreen { }
    }
}

private object MainScreenId {
    const val TITLE_LAYOUT_ID = "TITLE_LAYOUT_ID"
    const val CONTENT_LAYOUT_ID = "CONTENT_LAYOUT_ID"
    const val BUTTON_LAYOUT_ID = "BUTTON_LAYOUT_ID"
}