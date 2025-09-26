package com.example.bullseye.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.bullseye.R

@Composable
fun ResultDialog(
    dialogTitle: Int,
    hideDialog: () -> Unit,
    onRoundIncrement: () -> Unit,
    sliderValue: Int,
    points: Int,
) {
    AlertDialog(
        onDismissRequest = {
            hideDialog()
            onRoundIncrement()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    hideDialog()
                    onRoundIncrement()
                }
            ) {
                Text(text = stringResource(R.string.result_dialog_button_text))
            }
        },
        title = {
            Text(text = stringResource(dialogTitle))
        },
        text = {
            Text(text = stringResource(R.string.result_dialog_message, sliderValue, points))
        }
    )
}