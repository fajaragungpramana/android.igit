package com.github.fajaragungpramana.igit.module.main

sealed class MainState {

    data class AppTheme(val isDark: Boolean) : MainState()

}