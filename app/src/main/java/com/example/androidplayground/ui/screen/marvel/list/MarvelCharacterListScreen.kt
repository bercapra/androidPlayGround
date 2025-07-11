package com.example.androidplayground.ui.screen.marvel.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.androidplayground.R
import com.example.androidplayground.ui.component.BackgroundImage
import com.example.androidplayground.ui.component.ErrorDialog
import com.example.androidplayground.ui.component.Loader
import com.example.androidplayground.ui.component.MarvelCard
import com.example.androidplayground.ui.component.TitleText
import com.example.androidplayground.ui.screen.marvel.list.MarvelCharacterListScreenId.CONTENT_LAYOUT_ID
import com.example.androidplayground.ui.screen.marvel.list.MarvelCharacterListScreenId.TITLE_LAYOUT_ID
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme
import com.example.androidplayground.ui.theme.Dimens
import com.example.androidplayground.ui.util.OnLifecycleEventEffect
import com.example.androidplayground.ui.util.preview.previewCharacterDefault
import com.example.androidplayground.ui.util.preview.previewCharacterLongDescription
import com.example.androidplayground.ui.util.preview.previewCharacterNoDescription
import com.example.androidplayground.ui.util.preview.previewCharacterWithImage
import com.example.domain.entity.MarvelCharacter

@Composable
fun MarvelCharacterListScreen(
    viewModel: MarvelCharacterListViewModel = hiltViewModel(),
    onItemClicked: (marvelCharacter: MarvelCharacter) -> Unit,
    onError: () -> Unit
) {
    val data: MarvelCharacterListViewModel.CharacterListData =
        viewModel.state.collectAsState().value

    OnLifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.getCharacters()
    }

    BackgroundImage()

    when (data.state) {
        MarvelCharacterListViewModel.CharacterListState.LOADING -> {
            Loader()
        }

        MarvelCharacterListViewModel.CharacterListState.SHOW_CHARACTERS -> {
            ShowCharacters(data.characterList, onItemClicked)
        }

        MarvelCharacterListViewModel.CharacterListState.ERROR -> {
            data.exception?.let { ErrorDialog(it, onError) }
        }
    }
}

@Composable
private fun ShowCharacters(
    characterList: List<MarvelCharacter>,
    onItemClicked: (marvelCharacter: MarvelCharacter) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Dimens.MarvelCharacterListTopPadding,
                end = Dimens.MarvelPadding,
                bottom = Dimens.MarvelPadding
            ),
        constraintSet = getScreenConstrainSet()
    ) {
        TitleText(
            text = stringResource(id = R.string.marvel_character_list_screen_button),
            modifier = Modifier
                .layoutId(TITLE_LAYOUT_ID)
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .layoutId(CONTENT_LAYOUT_ID)
                .fillMaxWidth()
        ) {
            items(characterList.size) { index ->
                MarvelCard(
                    marvelCharacter = characterList[index],
                    onCardClicked = { marvelCharacter -> onItemClicked(marvelCharacter) }
                )
            }
        }
    }
}

private fun getScreenConstrainSet() = ConstraintSet {
    val title = createRefFor(TITLE_LAYOUT_ID)
    val content = createRefFor(CONTENT_LAYOUT_ID)

    createVerticalChain(title, content)

    constrain(title) {
        top.linkTo(parent.top)
        bottom.linkTo(content.top)
    }
    constrain(content) {
        start.linkTo(parent.start)
        top.linkTo(title.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCharacters() {
    AndroidPlayGroundTheme {
        ShowCharacters(
            listOf(
                previewCharacterDefault,
                previewCharacterWithImage,
                previewCharacterNoDescription,
                previewCharacterLongDescription
            )
        ) { }
    }
}

private object MarvelCharacterListScreenId {
    const val TITLE_LAYOUT_ID = "TITLE_LAYOUT_ID"
    const val CONTENT_LAYOUT_ID = "CONTENT_LAYOUT_ID"
}