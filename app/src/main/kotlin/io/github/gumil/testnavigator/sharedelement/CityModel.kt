package io.github.gumil.testnavigator.sharedelement

import android.support.annotation.DrawableRes
import io.github.gumil.testnavigator.R

internal enum class CityModel(
        @DrawableRes val drawableRes: Int = 0,
        val title: String
) {
    CHICAGO(R.drawable.chicago, "Chicago"),
    JAKARTA(R.drawable.jakarta, "Jakarta"),
    LONDON(R.drawable.london, "London"),
    SAO_PAULO(R.drawable.sao_paulo, "Sao Paulo"),
    TOKYO(R.drawable.tokyo, "Tokyo")
}