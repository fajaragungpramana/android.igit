package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import com.github.fajaragungpramana.igit.core.extension.connection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class UserRepository @Inject constructor(private val userDataSource: IUserDataSource) :
    IUserRepository {

    override suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<UserDetailResponse>> =
        Pager(PagingConfig(pageSize = 12)) { UserPagingSource(userDataSource, userRequest) }.flow

    override suspend fun getUser(username: String): Flow<AppResult<UserDetailResponse>> =
        connection {
            val response = userDataSource.userDetail(username)
            if (response.isSuccessful)
                AppResult.Success(response.body())
            else
                AppResult.Error(response.message())
        }

    override suspend fun getListRepo(userRequest: UserRequest): Flow<PagingData<RepoResponse>> =
        Pager(PagingConfig(pageSize = 12)) { RepoPagingSource(userDataSource, userRequest) }.flow

}