package com.example.bullseye

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.ui.theme.BullseyeTheme
import kotlin.random.Random

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableFloatStateOf(0.5f) }
    val sliderToInt = (sliderValue * 100).toInt()
    val targetValue by rememberSaveable { mutableIntStateOf(Random.nextInt(1, 100)) }

    fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference: Int = if (sliderToInt < targetValue) {
            targetValue - sliderToInt
        } else if (sliderToInt > targetValue) {
            sliderToInt - targetValue
        } else {
            0
        }
        return maxScore - difference

        //روش دوم
        /*    val maxScore = 100
            val difference = abs(targetValue - sliderToInt)
            val points = maxScore - difference
            if (points <= 0) {
                return 0
            }
            return points*/
    }

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
            GamePrompt(targetValue = targetValue)
            TargetSlider(
                value = sliderValue,
                onValueChange = { sliderValue = it }
            )
            Button(onClick = {
                alertIsVisible = true
            }) {
                Text(text = "HIT ME")
            }
        }
        Spacer(Modifier.weight(0.5f))
        if (alertIsVisible) {
            ResultDialog(
                hideDialog = { alertIsVisible = false },
                sliderValue = sliderToInt,
                points = pointsForCurrentRound()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    BullseyeTheme {
        GameScreen()
    }
}