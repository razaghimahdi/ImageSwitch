package com.razaghimahdi.image_switch.core

internal sealed class ImageSwitchStatus{

    object Checked: ImageSwitchStatus()

    object UnChecked: ImageSwitchStatus()

}
