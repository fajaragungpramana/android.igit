package com.github.fajaragungpramana.igit.module.favorite

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class FavoriteState {

    data class UserData(val pagingData: PagingData<User>) : FavoriteState()

    data class MessageData(val message: String) : FavoriteState()

}