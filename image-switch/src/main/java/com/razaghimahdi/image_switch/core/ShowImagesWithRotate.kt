package com.razaghimahdi.image_switch.core

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.razaghimahdi.image_switch.CustomImage


@Composable
internal fun RotateImages(
    currentStatus: ImageSwitchStatus,
    enabledImage: Painter,
    disabledImage: Painter,
    imageContentScale:ContentScale
) {

    RotateAnimation(currentStatus is ImageSwitchStatus.Checked){
        CustomImage(imageContentScale = imageContentScale, painter = enabledImage)
    }

    RotateAnimation(currentStatus is ImageSwitchStatus.UnChecked){
        CustomImage(imageContentScale = imageContentScale, painter = disabledImage)
    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun RotateAnimation(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        enter = slideInVertically(
            initialOffsetY = { it  },
            animationSpec = tween(durationMillis = 1000)
        )
                + slideInHorizontally(
            initialOffsetX = { it / 3 },
            animationSpec = tween(durationMillis = 1000)
        )
                + fadeIn(
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it / 3 },
            animationSpec = tween(durationMillis = 750)
        )
                + slideOutHorizontally(
            targetOffsetX = { it / 3 },
            animationSpec = tween(durationMillis = 750)
        )
                + fadeOut(
            animationSpec = tween(durationMillis = 750),

            ),
        visible = visible
    ) {
        content()
    }
}