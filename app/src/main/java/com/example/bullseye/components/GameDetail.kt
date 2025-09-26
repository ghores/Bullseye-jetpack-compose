package com.example.bullseye.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.R

@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    totalScore: Int = 0
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        Button(
            onClick = {}
        ) {
            Text(text = stringResource(R.string.start_over))
        }
        GameInfo(label = stringResource(R.string.score_label), value = totalScore)
        GameInfo(label = stringResource(R.string.current_round_label), value = 1)
        Button(
            onClick = {}
        ) {
            Text(text = stringResource(R.string.info))
        }
    }
}

@Composable
fun GameInfo(label: String, value: Int = 0) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Text(text = label)
        Text(text = value.toString())
    }
}

@Preview(showBackground = true)
@Composable
private fun GameDetailPreview() {
    GameDetail()
}