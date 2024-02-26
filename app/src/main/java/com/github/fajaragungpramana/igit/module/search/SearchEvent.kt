package com.github.fajaragungpramana.igit.module.search

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest

sealed class SearchEvent {

    data class SearchUser(val userRequest: UserRequest) : SearchEvent()

}