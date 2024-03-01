package com.github.fajaragungpramana.igit.module.detail

import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class DetailEvent {

    data class UserFavorite(val user: User) : DetailEvent()

    data class UserDetail(val username: String) : DetailEvent()

    data class UserIsFavorite(val username: String) : DetailEvent()

}
