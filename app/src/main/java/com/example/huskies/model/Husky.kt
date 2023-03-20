package com.example.huskies.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class Husky(
    @StringRes val nameRes: Int,
    @StringRes val nameRes02: Int = 0,
    @StringRes val descriptionRes: Int,
    @StringRes val descriptionRes02: Int = 0,
    @StringRes val eyeColor: Color,
    @StringRes val eyeColor02: Color = Color(0xFFFFFF),
    @DrawableRes val imageRes: Int
)