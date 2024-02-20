package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getListUser(userRequest: UserRequest): Flow<PagingData<UserResponse>>

}