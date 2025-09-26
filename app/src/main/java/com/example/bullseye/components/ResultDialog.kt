package com.example.bullseye.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.bullseye.R

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    sliderValue: Int,
    points: Int
) {
    AlertDialog(
        onDismissRequest = {
            hideDialog()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    hideDialog()
                }
            ) {
                Text(text = stringResource(R.string.result_dialog_button_text))
            }
        },
        title = {
            Text(text = stringResource(R.string.result_dialog_title))
        },
        text = {
            Text(text = stringResource(R.string.result_dialog_message, sliderValue, points))
        }
    )
}