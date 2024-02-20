package com.github.fajaragungpramana.igit.module.search

sealed class SearchEvent {

    data class SearchUser(val username: String?) : SearchEvent()

}