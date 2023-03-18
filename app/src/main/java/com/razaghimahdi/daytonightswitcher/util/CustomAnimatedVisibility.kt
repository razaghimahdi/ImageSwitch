package com.razaghimahdi.daytonightswitcher.util


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityWithFadeInExpandVerticallyAndFadeOutShrinkHorizontally(
    visible: Boolean,
    shouldRise: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        enter = slideInVertically(
            initialOffsetY = { if (shouldRise) it / 3 else -(it / 3) },
            animationSpec = tween(durationMillis = 1000)
        ) + fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { if (shouldRise) it / 3 else -(it / 3) },
            animationSpec = tween(durationMillis = 750)
        ) + fadeOut(
            animationSpec = tween(durationMillis = 750),

            ),
        visible = visible
    ) {
        content()
    }
}