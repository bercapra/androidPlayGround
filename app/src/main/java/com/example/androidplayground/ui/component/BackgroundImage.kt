package com.example.androidplayground.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidplayground.R
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.background_screen),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Preview
@Composable
private fun ContentTextPreview() {
    AndroidPlayGroundTheme {
        BackgroundImage()
    }
}
