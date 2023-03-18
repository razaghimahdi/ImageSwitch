package com.razaghimahdi.daytonightswitcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.razaghimahdi.daytonightswitcher.component.SwitchDayOrNightLogo
import com.razaghimahdi.daytonightswitcher.ui.theme.*
import com.razaghimahdi.image_switch.ImageSwitch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayToNightSwitcherTheme {

                val status: MutableState<SwitchStatus> =
                    remember { mutableStateOf(SwitchStatus.ScreenDay) }

                val shouldChangeBackground = remember { mutableStateOf(false) }

                val color1 = remember { Animatable(ColorDay01) }
                val color2 = remember { Animatable(ColorDay02) }


                if (shouldChangeBackground.value) {
                    LaunchedEffect(shouldChangeBackground) {
                        if (status.value is SwitchStatus.ScreenDay) {
                            color1.animateTo(ColorDay01, animationSpec = tween(700))
                            color2.animateTo(ColorDay02, animationSpec = tween(700))
                        } else {
                            color1.animateTo(ColorNight01, animationSpec = tween(700))
                            color2.animateTo(ColorNight02, animationSpec = tween(700))
                        }
                        shouldChangeBackground.value = false
                    }
                }

                StatelessScreen(
                    status = { status.value },
                    color1 = { color1.value },
                    color2 = { color2.value },
                    shouldChangeBackgroundOnExecute = { shouldChangeBackground.value = true },
                    statusOnExecute = { value ->
                        status.value =
                            if (value) SwitchStatus.ScreenDay else SwitchStatus.ScreenNight
                    },
                )

            }
        }
    }

    @Composable
    private fun StatelessScreen(
        color1: () -> Color,
        color2: () -> Color,
        shouldChangeBackgroundOnExecute: () -> Unit,
        status: () -> SwitchStatus,
        statusOnExecute: (Boolean) -> Unit
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(color1(), color2()),
                            // start = Offset(0f, 0f), // top left corner
                            // end = Offset(boxSize, boxSize) // bottom right corner
                        )
                    )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SwitchDayOrNightLogo(status = { status() })

                   ImageSwitch(
                        checkedImage = painterResource(R.drawable.good_morning),
                        unCheckedImage = painterResource(R.drawable.good_night),
                        size = 150.dp,
                        checked = status() is SwitchStatus.ScreenDay,
                        onCheckedChange = {
                            shouldChangeBackgroundOnExecute()
                            statusOnExecute(it)
                        }
                    )
                }

            }
        }
    }



    override fun onStart() {
        super.onStart()
        hideUISystem()
    }

    private fun hideUISystem() {
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView) ?: return
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

}

