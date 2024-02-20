package com.github.fajaragungpramana.igit.core.data.remote.user

import com.github.fajaragungpramana.igit.core.data.remote.user.request.UserRequest
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDataResponse
import com.github.fajaragungpramana.igit.core.data.remote.user.response.UserDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IUserDataSource {

    @GET("search/users")
    suspend fun searchUser(@QueryMap userRequest: UserRequest): Response<UserDataResponse>

    @GET("users/{username}")
    suspend fun userDetail(@Path("username") username: String): Response<UserDetailResponse>

}