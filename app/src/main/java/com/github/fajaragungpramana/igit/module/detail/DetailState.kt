package com.github.fajaragungpramana.igit.module.detail

import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class DetailState {

    data class UserLoading(val isLoading: Boolean) : DetailState()

    data class UserData(val user: User) : DetailState()

    data class UserFavorite(val value: Boolean) : DetailState()

    data class MessageData(val message: String) : DetailState()

}