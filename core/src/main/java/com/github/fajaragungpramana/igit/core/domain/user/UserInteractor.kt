package com.github.fajaragungpramana.igit.core.domain.user

import androidx.paging.PagingData
import androidx.paging.map
import com.github.fajaragungpramana.igit.core.data.remote.user.IUserRepository
import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: IUserRepository) : UserUseCase {

    override suspend fun getListUser(userRequest: UserRequest): Flow<PagingData<User>> = channelFlow {
        userRepository.getListUser(userRequest).map { paging ->
            send(paging.map { User.mapToObject(it) })
        }
    }.flowOn(Dispatchers.IO)

}