package com.example.bullseye.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bullseye.R
import com.example.bullseye.components.GameDetail
import com.example.bullseye.components.GamePrompt
import com.example.bullseye.components.ResultDialog
import com.example.bullseye.components.TargetSlider
import com.example.bullseye.ui.theme.BullseyeTheme
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableFloatStateOf(0.5f) }
    var targetValue by rememberSaveable { mutableIntStateOf(Random.nextInt(1, 100)) }
    var totalScore by rememberSaveable { mutableIntStateOf(0) }
    var currentRound by rememberSaveable { mutableIntStateOf(1) }
    val sliderToInt = (sliderValue * 100).toInt()

    fun differenceAmount(): Int = abs(targetValue - sliderToInt)
    fun newTargetValue(): Int = Random.nextInt(1, 100)
    fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference = differenceAmount()
        val points = maxScore - difference
        var bonus = 0
        if (difference == 0) {
            bonus = 100
        } else if (difference == 1) {
            bonus = 50
        }
        return points + bonus

        //روش دوم
        /*    val maxScore = 100
            val difference: Int = if (sliderToInt < targetValue) {
                targetValue - sliderToInt
            } else if (sliderToInt > targetValue) {
                sliderToInt - targetValue
            } else {
                0
            }
            return maxScore - difference*/
    }

    fun startNewGame() {
        totalScore = 0
        currentRound = 1
        sliderValue = 0.5f
        targetValue = newTargetValue()
    }

    fun alertTitle(): Int {
        val difference = differenceAmount()
        val title = when {
            difference == 0 -> R.string.alert_title_1
            difference < 5 -> R.string.alert_title_2
            difference <= 10 -> R.string.alert_title_3
            else -> R.string.alert_title_4
        }
        return title
    }

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.background_image)
        )
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
                Button(
                    onClick = {
                        alertIsVisible = true
                        totalScore += pointsForCurrentRound()
                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = stringResource(R.string.hit_me_button_text))
                }
                GameDetail(
                    modifier = Modifier.fillMaxWidth(),
                    totalScore = totalScore,
                    round = currentRound,
                    onStartOver = { startNewGame() }
                )
            }
            Spacer(Modifier.weight(0.5f))
            if (alertIsVisible) {
                ResultDialog(
                    dialogTitle = alertTitle(),
                    hideDialog = { alertIsVisible = false },
                    onRoundIncrement = {
                        currentRound++
                        targetValue = newTargetValue()
                    },
                    sliderValue = sliderToInt,
                    points = pointsForCurrentRound()
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 864,
    heightDp = 432
)
@Composable
fun GameScreenPreview() {
    BullseyeTheme {
        GameScreen()
    }
}