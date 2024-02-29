package com.github.fajaragungpramana.igit.core.domain.user

import androidx.paging.PagingData
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.local.sql.entity.UserEntity
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.model.Repo
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<User>>

    suspend fun getUser(username: String): Flow<AppResult<User>>

    suspend fun getListRepo(userRequest: UserRequest): Flow<PagingData<Repo>>

    suspend fun getListFavoriteUser(): Flow<AppResult<PagingData<User>>>

    suspend fun isFavoriteUser(username: String): Flow<Boolean>

    suspend fun saveFavoriteUser(userEntity: UserEntity): Flow<Unit>

}