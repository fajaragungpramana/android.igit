package com.github.fajaragungpramana.igit.module.follower

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest

sealed class FollowerEvent {

    data class ListFollower(val userRequest: UserRequest) : FollowerEvent()

}