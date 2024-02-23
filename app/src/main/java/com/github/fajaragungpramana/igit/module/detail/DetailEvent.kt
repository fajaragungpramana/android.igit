package com.github.fajaragungpramana.igit.module.detail

sealed class DetailEvent {

    data class User(val username: String) : DetailEvent()

}
