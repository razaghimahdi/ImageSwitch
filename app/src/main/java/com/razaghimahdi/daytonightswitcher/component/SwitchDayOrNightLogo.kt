package com.razaghimahdi.daytonightswitcher.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.razaghimahdi.daytonightswitcher.R
import com.razaghimahdi.daytonightswitcher.SwitchStatus
import com.razaghimahdi.daytonightswitcher.util.AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally


@Composable
fun SwitchDayOrNightLogo(status: () -> SwitchStatus) {
    val painterDay = painterResource(R.drawable.day)
    val painterNight = painterResource(R.drawable.night)

    Box(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
            .padding(16.dp) ,
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally(
                status() is SwitchStatus.ScreenDay,
                true
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterDay,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally(
                status() is SwitchStatus.ScreenNight,
                false
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterNight,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }


        SwitchDayOrNightText(status = { status() })

    }

}


