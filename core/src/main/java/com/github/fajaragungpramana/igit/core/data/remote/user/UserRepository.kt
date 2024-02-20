package com.github.fajaragungpramana.igit.core.data.remote.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class UserRepository @Inject constructor(private val userDataSource: IUserDataSource) :
    IUserRepository {

    override fun getListUser(userRequest: UserRequest): Flow<PagingData<UserDetailResponse>> =
        Pager(PagingConfig(pageSize = 30)) { UserPagingSource(userDataSource, userRequest) }
            .flow

}