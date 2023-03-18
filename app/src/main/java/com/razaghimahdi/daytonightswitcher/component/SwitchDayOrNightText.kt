package com.razaghimahdi.daytonightswitcher.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.razaghimahdi.daytonightswitcher.R
import com.razaghimahdi.daytonightswitcher.SwitchStatus
import com.razaghimahdi.daytonightswitcher.util.AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally


@Composable
fun SwitchDayOrNightText(status: () -> SwitchStatus) {

    Box(
        modifier = Modifier
            .fillMaxWidth() ,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally(
            status() is SwitchStatus.ScreenDay,
            true
        ) {
            Text(text = "Good Morning!", style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.background)
        }
        AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally(
            status() is SwitchStatus.ScreenNight,
            false
        ) {
            Text(text = "Good Night!", style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.background)
        }
    }
}