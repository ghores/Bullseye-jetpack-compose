package com.example.bullseye

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.ui.theme.BullseyeTheme

@Composable
fun TargetSlider(
    modifier: Modifier = Modifier,
    value: Float = 0.5f,
    onValueChange: (Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.min_value_text),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp)
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0.01f..1f,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = stringResource(R.string.max_value_text),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TargetSliderPreview() {
    BullseyeTheme {
        TargetSlider(value = 0.5f, onValueChange = {})
    }
}