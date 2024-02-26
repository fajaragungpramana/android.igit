package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<UserDetailResponse>>

    suspend fun getUser(username: String): Flow<AppResult<UserDetailResponse>>

    suspend fun getListRepo(userRequest: UserRequest): Flow<PagingData<RepoResponse>>

}