package com.github.fajaragungpramana.igit.module.favorite

sealed class FavoriteEvent {

    data object ListUser : FavoriteEvent()

}