package com.github.fajaragungpramana.igit.module.follower

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class FollowerState {

    data class UserData(val pagingData: PagingData<User>) : FollowerState()

}