package com.github.fajaragungpramana.igit.module.follow

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class FollowState {

    data class UserData(val pagingData: PagingData<User>) : FollowState()

}