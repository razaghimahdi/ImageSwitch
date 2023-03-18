package com.razaghimahdi.daytonightswitcher

sealed class SwitchStatus{

    object ScreenDay: SwitchStatus(){
        override fun toString(): String {
            return "ScreenDay"
        }
    }

    object ScreenNight : SwitchStatus(){
        override fun toString(): String {
            return "ScreenNight"
        }
    }

}
