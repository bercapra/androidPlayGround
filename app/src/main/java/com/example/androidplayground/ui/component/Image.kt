package com.example.androidplayground.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import coil.compose.SubcomposeAsyncImage
import com.example.androidplayground.ui.theme.Dimens

@Composable
fun RemoteImage(imgPath: String, contentDescription: String, modifier: Modifier) {
    SubcomposeAsyncImage(
        model = imgPath,
        contentDescription = contentDescription,
        modifier = modifier
            .width(Dimens.RemoteImageWidth)
            .height(Dimens.RemoteImageHeight)
            .clip(
                RoundedCornerShape(
                    topStart = Dimens.RemoteImageRoundedCornerShape,
                    topEnd = Dimens.RemoteImageRoundedCornerShape
                )
            )
            .background(Color.Black)
            .border(BorderStroke(Dimens.BorderStroke, Color.Black)),
        loading = {
            Loader()
        }
    )
}
