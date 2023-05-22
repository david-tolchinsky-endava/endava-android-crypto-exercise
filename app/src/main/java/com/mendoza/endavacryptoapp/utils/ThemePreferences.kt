package com.mendoza.endavacryptoapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class ThemePreferences(activity: Activity) {
    private val sharedPref: SharedPreferences

    init {
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
    }

    fun readCurrentTheme():Themes {
        return if(sharedPref.getInt(KEY_SELECTED_THEME, 0) == Themes.DAY.ordinal) Themes.DAY else Themes.NIGHT
    }

    fun setTheme(theme:Themes) {
        with(sharedPref.edit()) {
            putInt(KEY_SELECTED_THEME, theme.ordinal)
            apply()
        }
    }

    companion object {
        const val KEY_SELECTED_THEME = "SELECTED_THEME"
    }
}

enum class Themes {
    DAY, NIGHT
}