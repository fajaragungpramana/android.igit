package com.github.fajaragungpramana.igit.core.domain.user

import androidx.paging.PagingData
import androidx.paging.map
import com.github.fajaragungpramana.igit.core.app.AppResult
import com.github.fajaragungpramana.igit.core.data.remote.user.IUserRepository
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.model.Repo
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) :
    UserUseCase {

    override suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<User>> = channelFlow {
        userRepository.getListUser(userRequest).collectLatest {
            send(it.map { response -> User.mapToObject(response) })
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getUser(username: String): Flow<AppResult<User>> = channelFlow<AppResult<User>> {
        userRepository.getUser(username).collectLatest {
            when (it) {
                is AppResult.Success -> send(AppResult.Success(User.mapToObject(it.data)))
                is AppResult.Error -> send(AppResult.Error(it.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getListRepo(userRequest: UserRequest): Flow<PagingData<Repo>> = channelFlow<PagingData<Repo>> {
        userRepository.getListRepo(userRequest).collectLatest {
            send(it.map { response -> Repo.mapToObject(response) })
        }
    }.flowOn(Dispatchers.IO)

}