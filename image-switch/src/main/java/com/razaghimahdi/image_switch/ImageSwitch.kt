package com.razaghimahdi.image_switch

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.razaghimahdi.image_switch.core.ImageSwitchAnimation
import com.razaghimahdi.image_switch.core.ImageSwitchStatus
import com.razaghimahdi.image_switch.core.RotateImages

@Composable
fun ImageSwitch(
    modifier: Modifier = Modifier,
    checkedImage: Painter,
    unCheckedImage: Painter,
    imageContentScale: ContentScale = ContentScale.Crop,
    size: Dp = 50.dp,
    borderStroke: BorderStroke = BorderStroke(width = 1.dp, color = Color.White),
    thumbColor: Color = Color.White,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {


    val switchBorder = if (borderStroke.width < 1.dp) modifier else modifier.border(
        border = borderStroke,
        shape = RoundedCornerShape(100)
    )

    val switchSize by remember {
        mutableStateOf(size)
    }

    val currentStatus: MutableState<ImageSwitchStatus> = remember {
        if (!checked) mutableStateOf(ImageSwitchStatus.UnChecked) else mutableStateOf(
            ImageSwitchStatus.Checked
        )
    }

    val transition = updateTransition(currentStatus.value, label = "SwitchState")

    val interactionSource = remember { MutableInteractionSource() }

    val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        currentStatus.value = if (currentStatus.value == ImageSwitchStatus.UnChecked) {
            onCheckedChange(true)
            ImageSwitchStatus.Checked
        } else {
            onCheckedChange(false)
            ImageSwitchStatus.UnChecked
        }
    }

    Box(
        modifier = switchBorder
            .then(clickable)
            .indication(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(
                    bounded = true,
                    radius = 100.dp,
                    color = Color.Transparent
                )
            )
    ) {
        BoxWithConstraints(
            modifier = modifier
                .width(switchSize - 3.dp)
                .height(switchSize / 2)
                .indication(MutableInteractionSource(), null)
                .background(color = Color.Transparent, shape = RoundedCornerShape(100)),
            contentAlignment = Alignment.CenterStart
        ) {

            val roundCardSize = this.maxWidth / 2

            val xOffset by transition.animateDp(
                transitionSpec = {
                    tween(150, easing = LinearOutSlowInEasing)
                }, label = "xOffset"
            ) { state ->
                when (state) {
                    ImageSwitchStatus.UnChecked -> borderStroke.width
                    ImageSwitchStatus.Checked -> this.maxWidth - roundCardSize
                }
            }

            ShowImages(
                enabledImage = checkedImage,
                disabledImage = unCheckedImage,
                imageSwitchAnimation = ImageSwitchAnimation.None,
                currentStatus = currentStatus.value,
                imageContentScale = imageContentScale,
            )


            Card(
                modifier = Modifier
                    .size((this.maxWidth / 2) - (borderStroke.width))
                    .offset(x = xOffset, y = 0.dp)
                    .padding(3.dp), elevation = 3.dp,
                shape = RoundedCornerShape(100),
                backgroundColor = thumbColor,
                border = BorderStroke(
                    if (currentStatus.value == ImageSwitchStatus.Checked) 0.5.dp else 0.dp,
                    color = Color.LightGray
                )
            ) {


            }

        }
    }

}

@Composable
 private fun ShowImages(
    enabledImage: Painter,
    disabledImage: Painter,
    imageSwitchAnimation: ImageSwitchAnimation,
    currentStatus: ImageSwitchStatus,
    imageContentScale: ContentScale
) {

    if (currentStatus == ImageSwitchStatus.Checked) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(100)),
            contentScale = imageContentScale,
            painter = enabledImage,
            contentDescription = null
        )
    } else {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(100)),
            contentScale = imageContentScale,
            painter = disabledImage,
            contentDescription = null
        )
    }

}

@Composable
internal fun CustomImage(imageContentScale: ContentScale, painter: Painter) {

    Image(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(100)),
        contentScale = imageContentScale,
        painter = painter,
        contentDescription = null
    )
}
