package com.github.fajaragungpramana.igit.core.data.remote.user

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IUserDataSource {

    @GET("search/users")
    suspend fun searchUser(@QueryMap userRequest: UserRequest): Response<UserDataResponse>

}