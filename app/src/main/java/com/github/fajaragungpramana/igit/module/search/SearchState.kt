package com.github.fajaragungpramana.igit.module.search

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.domain.user.model.User

sealed class SearchState {

    data class UserData(val pagingData: PagingData<User>) : SearchState()

}