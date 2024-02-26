package com.github.fajaragungpramana.igit.module.follow

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest

sealed class FollowEvent {

    data class ListFollow(val userRequest: UserRequest) : FollowEvent()

}