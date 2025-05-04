package com.example.androidplayground.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androidplayground.R
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.Red,
        fontSize = dimensionResource(R.dimen.title_text_size).value.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun TitleTextPreview() {
    AndroidPlayGroundTheme {
        TitleText(
            text = stringResource(R.string.title),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ContentText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.Red,
        fontSize = dimensionResource(R.dimen.content_text_size).value.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun ContentTextPreview() {
    AndroidPlayGroundTheme {
        ContentText(
            text = stringResource(R.string.content),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CardContentText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White,
        fontSize = dimensionResource(R.dimen.card_content_text_size).value.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CardContentTextPreview() {
    AndroidPlayGroundTheme {
        CardContentText(
            text = stringResource(R.string.content),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CardDescriptionText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White,
        fontSize = dimensionResource(R.dimen.card_description_text_size).value.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CardDescriptionTextPreview() {
    AndroidPlayGroundTheme {
        CardDescriptionText(
            text = stringResource(R.string.content),
            modifier = Modifier.fillMaxWidth()
        )
    }
}