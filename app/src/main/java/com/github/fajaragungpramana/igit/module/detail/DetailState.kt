package com.github.fajaragungpramana.igit.module.detail

import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class DetailState {

    data class UserData(val user: User) : DetailState()

    data class MessageData(val message: String) : DetailState()

}