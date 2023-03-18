package com.razaghimahdi.image_switch.core

sealed class ImageSwitchAnimation{

    object None: ImageSwitchAnimation()

    object Rotate: ImageSwitchAnimation()

    object Swipe: ImageSwitchAnimation()

    object Fade: ImageSwitchAnimation()

}
