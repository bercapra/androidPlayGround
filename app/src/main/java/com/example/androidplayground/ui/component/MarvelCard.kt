package com.example.androidplayground.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androidplayground.R
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme
import com.example.androidplayground.ui.theme.Dimens
import com.example.androidplayground.ui.theme.GradientEnd
import com.example.androidplayground.ui.theme.GradientStart
import com.example.domain.entity.MarvelCharacter

@Composable
fun MarvelCard(
    marvelCharacter: MarvelCharacter,
    onCardClicked: (marvelCharacter: MarvelCharacter) -> Unit
) {
    val horizontalGradient = Brush.horizontalGradient(colors = listOf(GradientStart, GradientEnd))

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onCardClicked(marvelCharacter) }
            .padding(vertical = Dimens.MarvelPadding),
        shape = RoundedCornerShape(
            topEnd = Dimens.RoundedCornerShape,
            bottomEnd = Dimens.RoundedCornerShape
        ),
        border = BorderStroke(Dimens.BorderStroke, Color.Black)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = horizontalGradient)
                .padding(Dimens.MarvelPadding),
        ) {
            val (image, name) = createRefs()
            createVerticalChain(image, name)

            RemoteImage(
                imgPath = marvelCharacter.img,
                contentDescription = stringResource(R.string.empty_value),
                modifier = Modifier
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(name.top)
                    }
            )
            CardContentText(
                text = marvelCharacter.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.MarvelPadding)
                    .constrainAs(name) {
                        start.linkTo(parent.start)
                        top.linkTo(image.bottom)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarvelCardPreview() {
    AndroidPlayGroundTheme {
        MarvelCard(
            MarvelCharacter(
                integerResource(R.integer.hero_muri_man_id),
                stringResource(R.string.hero_muri_man),
                stringResource(R.string.hero_description_muri_man),
                stringResource(R.string.empty_value)
            )
        ) { }
    }
}