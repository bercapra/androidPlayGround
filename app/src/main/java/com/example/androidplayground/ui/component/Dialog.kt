package com.example.androidplayground.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidplayground.R
import com.example.androidplayground.ui.theme.AndroidPlayGroundTheme

@Composable
fun ErrorDialog(exception: Exception, onError: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onError()
                    }
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        onError()
                    }
                ) {
                    Text(text = stringResource(R.string.dismiss))
                }
            },
            icon = {
                Icon(
                    Icons.Filled.Warning,
                    contentDescription = stringResource(R.string.warning)
                )
            },
            title = {
                Text(text = stringResource(R.string.something_got_wrong))
            },
            text = {
                Text(text = exception.message ?: stringResource(R.string.broken_message))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorDialogPreview() {
    AndroidPlayGroundTheme {
        ErrorDialog(Exception(stringResource(R.string.broken_message))) { }
    }
}