package com.github.fajaragungpramana.igit.core.domain.user

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<User>>

}