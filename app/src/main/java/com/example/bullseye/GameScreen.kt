package com.example.bullseye

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bullseye.ui.theme.BullseyeTheme

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.weight(0.5f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(9f)
        ) {
            Text(text = "PUT THE BULLSEYE AS CLOSE AS YOU CAN TO")
            Text(text = "89", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "1",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Slider(
                    value = 0.5f,
                    onValueChange = {},
                    valueRange = 0.01f..1f,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "100",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            Button(onClick = { }) {
                Text(text = "HIT ME")
            }
        }
        Spacer(Modifier.weight(0.5f))
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    BullseyeTheme {
        GameScreen()
    }
}