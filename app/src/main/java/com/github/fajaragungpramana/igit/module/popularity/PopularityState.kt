package com.github.fajaragungpramana.igit.module.popularity

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class PopularityState {

    data class UserData(val pagingData: PagingData<User>) : PopularityState()

}