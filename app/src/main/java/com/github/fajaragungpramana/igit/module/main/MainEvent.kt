package com.github.fajaragungpramana.igit.module.main

sealed class MainEvent {

    data class Theme(val isDark: Boolean): MainEvent()

}