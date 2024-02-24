package com.github.fajaragungpramana.igit.module.popularity

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest

sealed class PopularityEvent {

    data class ListUser(val userRequest: UserRequest) : PopularityEvent()

}