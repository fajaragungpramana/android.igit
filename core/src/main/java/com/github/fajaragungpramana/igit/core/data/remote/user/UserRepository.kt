package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.RepoResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

data class UserRepository @Inject constructor(private val userDataSource: IUserDataSource) :
    IUserRepository {

    override suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<UserDetailResponse>> =
        Pager(PagingConfig(pageSize = 12)) { UserPagingSource(userDataSource, userRequest) }
            .flow

    override suspend fun getUser(username: String): Flow<AppResult<UserDetailResponse>> =
        channelFlow {
            val response = userDataSource.userDetail(username)
            if (response.isSuccessful)
                send(AppResult.Success(response.body()))
            else
                send(AppResult.Error(response.message()))
        }.flowOn(Dispatchers.IO)

    override suspend fun getListRepo(userRequest: UserRequest): Flow<PagingData<RepoResponse>> =
        Pager(PagingConfig(pageSize = 12)) { RepoPagingSource(userDataSource, userRequest) }
            .flow

}